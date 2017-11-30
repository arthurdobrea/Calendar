package com.calendar.project.controller;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.service.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;



    private static final Logger LOGGER = Logger.getLogger(EventController.class);

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        LOGGER.info("Request of \"/events\" page GET");
        model.addAttribute("events", eventService.getAllEvents());
        LOGGER.info("Opening of \"/events\" page");
        return "events";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String showAllEvents(Model model, String filterByKeyword) {
        LOGGER.info("Request of \"/events\" page POST");
        model.addAttribute("filterByKeyword", filterByKeyword);

        model.addAttribute("events", eventService.getEventsByKeyword(filterByKeyword));
        LOGGER.info("Opening of \"/events\" page");
        return "events";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.GET)
    public String updateEvent(int eventId, Model model) {
        LOGGER.info("Request of \"/updateEvent\" page GET");
        model.addAttribute("eventForm", eventService.getEvent(eventId));
        LOGGER.info("Opening of \"/updateEvent\" page");
        return "updateEvent";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateEvent(@ModelAttribute("eventForm") Event eventForm, Model model) {
        LOGGER.info("Request of \"/updateEvent\" page POST");
        List<User> participants = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participants.add(userDao.getUser(u.getId()));
        }

        eventForm.setParticipants(participants);
        model.addAttribute("eventForm", eventForm);
        eventService.updateEvent(eventForm);
        LOGGER.info("Redirect to \"/userPage\" page");
        return "redirect:/userPage";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
    public String deleteEvent(int eventId, Model model) {
        LOGGER.info("Request of \"/deleteEvent\" page GET");
        model.addAttribute("eventForm", eventService.getEvent(eventId));
        LOGGER.info("Opening of \"/deleteEvent\" page");
        return "deleteEvent";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
    public String deleteEvent(@ModelAttribute("eventForm") Event event) {
        LOGGER.info("Request of \"/deleteEvent\" page POST");
        eventService.deleteEvent(event);
        LOGGER.info("Redirect to \"/userPage\" page");
        return "redirect:/userPage";
    }


    @RequestMapping(value = "/createEvent", method = RequestMethod.GET)
    public String createEvent(Model model) {
        LOGGER.info("Request of \"/createEvent\" page GET");
        System.out.println("tags="+tagService.getAllTags());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("eventTypes", eventService.getEventTypeList());
        LOGGER.info("Opening of \"/createEvent\" page");
        return "createEvent";
    }

    @MessageMapping("/notification")
    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String createEvent(Model model, @ModelAttribute("title") String title,
                              @ModelAttribute("location") String location,
                              @ModelAttribute("description") String description,
                              @ModelAttribute("start") String startDate,
                              @ModelAttribute("end") String endDate,
                              @ModelAttribute("participants") String participantsList,
                              @ModelAttribute("checkSubscribe") String checkSubscribe,
                              @ModelAttribute("checkParticipants") String checkParticipants,
                              @ModelAttribute("eventType") EventType eventType,
                              @RequestParam("checkboxTags")List<String> checkboxValue,
                              RedirectAttributes redirectAttributes
    ) {
        boolean allday=false;
        System.out.println("participantsList" +participantsList);
        if (startDate.length()<15){
            startDate+=" 10:00";
            endDate+=" 17:00";
            allday=true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LOGGER.info("Request of \"/createEvent\" page POST");
        List<Notification> notifications = new ArrayList<>();
        List<User> participants=userService.parseStringToUsersList(participantsList);
        Event event = new Event(title,eventType,securityService.findLoggedInUsername(),location, participants,
                LocalDateTime.parse(startDate, formatter),LocalDateTime.parse(endDate, formatter),
                allday,LocalDateTime.now(),description,tagService.parseListOfStringToSetOfTag(checkboxValue));

        eventService.saveEvent(event);;
        if (checkSubscribe.equals("on")) emailService.mailParticipantsNewEvent(event);
        if (checkParticipants.equals("on")) emailService.mailSubscribersNewEvent(event);
        for (User u : participants) {
            final Notification notification = new Notification(u, event);
            notifications.add(notification);
        }
//        notificationService.saveAll(notifications);
        model.addAttribute("eventForm", event);
        redirectAttributes.addAttribute("eventId", event.getId());

        notificationService.saveAll(notifications);
        notificationService.sendToAllParticipants(participants, event);
        //notificationService.sendToSpecificUser();

        LOGGER.info("Redirect to \"/showEvent\" page");
        return "redirect:/showEvent";
    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public String showEvent(Model model, int  eventId) {
        LOGGER.info("Request of \"/showEvent\" page GET");
        Event event = eventService.getEvent(eventId);
        User user =securityService.findLoggedInUsername();
        boolean isParticipant=userService.isUserParticipant(event,user);
        System.out.println(event);
//        Notification notification = notificationService.getNotification(securityService.findLoggedInUsername(), event);
//        notificationService.changeState(notification);
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("EEEE, d, MMMM ,yyyy, 'Time:'  KK:MM a ");

        model.addAttribute("start", event.getStart().format(formatter));
        model.addAttribute("end", event.getStart().format(formatter));
        model.addAttribute("isParticipant", isParticipant);
//        model.addAttribute("image", Base64.encode(userService.getUser(1).getImage()));
        model.addAttribute("event", event);
        LOGGER.info("Opening of \"/showEvent\" page");
//        redirectAttributes.addAttribute("eventId", event.getId());
        return "showEvent";
    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.POST)
    public String suscribeToEvent(Model model, @ModelAttribute("id") int eventId) {
        LOGGER.info("Request of \"/showEvent\" page GET");
        Event event = eventService.getEvent(eventId);
        User user =securityService.findLoggedInUsername();
        if (userService.isUserParticipant(event,user)) {
            System.out.println("found "+user.getFullName());
            event.getParticipants().remove(user);
        }else {
            event.getParticipants().add(user);
            System.out.println("NOT found "+user.getFullName());
        }

        eventService.updateEvent(event);
//        Notification notification = notificationService.getNotification(securityService.findLoggedInUsername(), event);
//        notificationService.changeState(notification);
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("EEEE, d, MMMM ,yyyy, 'Time:'  KK:MM a ");

        LOGGER.info("Opening of \"/showEvent\" page");

        return "userPage";
    }

    @RequestMapping(value = "/getParticipantsByEvent", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String getParticipantsInJSON(@RequestParam("eventId") int eventId) {
        LOGGER.info("Receives ID of event " + eventId);

        List<User> participantsByEvent = eventService.getParticipantsByEvent(eventId);

        JsonArray participantsArray = new JsonArray();
        for (User u : participantsByEvent) {
            JsonObject userAsJson = new JsonObject();
            userAsJson.addProperty("id", u.getId());
            userAsJson.addProperty("firstname", u.getFirstname());
            userAsJson.addProperty("lastname", u.getLastname());
            participantsArray.add(userAsJson);
        }
        LOGGER.info("Returns list of participants at event (Count = " + participantsByEvent.size() + ")");
        return participantsArray.toString();
    }


    @RequestMapping(value = "/editEvent", method = RequestMethod.GET)
    public String createEvent(Model model,  int eventId){
        LOGGER.info("Request of \"/editEvent\" page GET");
        Event event = eventService.getEvent(eventId);
        model.addAttribute("event", event);
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("eventTypes", eventService.getEventTypeList());
        System.out.println("event.getEventTagsAsEnum()=="+event.getEventTagsAsEnum());
        LOGGER.info("Redirect to \"/editEvent\" page");
        return "/editEvent";
    }

    @RequestMapping(value = "/editEvent", method = RequestMethod.POST)
    public String editvent(Model model,
                           @ModelAttribute("event-id") int id,
                           @ModelAttribute("title") String title,
                              @ModelAttribute("location") String location,
                              @ModelAttribute("description") String description,
                              @ModelAttribute("start") String startDate,
                              @ModelAttribute("end") String endDate,
                              @ModelAttribute("participants") String participantsList,
                              @ModelAttribute("checkSubscribe") String checkSubscribe,
                              @ModelAttribute("checkParticipants") String checkParticipants,
                              @ModelAttribute("eventType") EventType eventType,
                              @RequestParam("checkboxTags")List<String> checkboxValue,
                              RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Request of \"/editEvent\" page POST");
        List<Notification> notifications = new ArrayList<>();
        List<User> participants=userService.parseStringToUsersList(participantsList);
        Event event = eventService.getEvent(id);
        event.setTitle(title);
        event.setEventType(eventType);
        event.setAuthor( securityService.findLoggedInUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        event.setStart(LocalDateTime.parse(startDate, formatter));
        event.setEnd(LocalDateTime.parse(endDate, formatter));
        event.setLocation(location);
        event.setEventCreated(LocalDateTime.now());
        event.setDescription(description);
        event.setParticipants(participants);
        event.setTags(tagService.parseListOfStringToSetOfTag(checkboxValue));
        eventService.updateEvent(event);
        if (checkSubscribe.equals("on")) emailService.mailParticipantsNewEvent(event);
        if (checkParticipants.equals("on")) emailService.mailSubscribersNewEvent(event);
        for (User u : participants) {
            final Notification notification = new Notification(u, event);
            notifications.add(notification);
        }
//        notificationService.saveAll(notifications);
        model.addAttribute("eventForm", event);
        redirectAttributes.addAttribute("eventId", event.getId());

        notificationService.saveAll(notifications);
        notificationService.sendToAllParticipants(participants, event);
        //notificationService.sendToSpecificUser();

        LOGGER.info("Redirect to \"/showEvent\" page");
        return "redirect:/showEvent";
    }




}
