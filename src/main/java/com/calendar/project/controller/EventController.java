package com.calendar.project.controller;


import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import com.calendar.project.service.MobilePushNotificationsService;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.service.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class EventController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    MobilePushNotificationsService mobilePushNotificationsService;



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
        System.out.println("participantsList" + participantsList);
        if (startDate.length()<15){
            startDate+=" 10:00";
            endDate+=" 18:00";
            allday=true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LOGGER.info("Request of \"/createEvent\" page POST");
        List<User> participants=userService.parseStringToUsersList(participantsList);
        Event event = new Event(title,eventType,securityService.findLoggedInUsername(),location, participants,
                LocalDateTime.parse(startDate, formatter),LocalDateTime.parse(endDate, formatter),
                allday,LocalDateTime.now(),description,tagService.parseListOfStringToSetOfTag(checkboxValue));

        eventService.saveEvent(event);

        if (checkSubscribe.equals("on")) emailService.mailParticipantsNewEvent(event);
        if (checkParticipants.equals("on")) emailService.mailSubscribersNewEvent(event);
        List<Notification> finalNotifications = eventService.notificationCreator(event);
        model.addAttribute("eventForm", event);
        redirectAttributes.addAttribute("eventId", event.getId());

        notificationService.saveAll(finalNotifications);
        notificationService.sendToAllParticipants(event.getParticipants(), event);

        LOGGER.info("Redirect to \"/index\" page");
        return "redirect:/index";
    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public String showEvent(Model model, int  eventId) {
        LOGGER.info("Request of \"/showEvent\" page GET");
        Event event = eventService.getEvent(eventId);
        User user =securityService.findLoggedInUsername();
        boolean isParticipant=userService.isUserParticipant(event,user);
        System.out.println(event);
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, 'Time:'  kk:mm");

        Notification notification = notificationService.getNotification(securityService.findLoggedInUsername(), event);
        notificationService.changeState(notification);

        model.addAttribute("start", event.getStart().format(formatter));
        model.addAttribute("end", event.getEnd().format(formatter));
        model.addAttribute("isParticipant", isParticipant);
        model.addAttribute("created", event.getEventCreated().format(formatter));
        model.addAttribute("event", event);
        LOGGER.info("Opening of \"/showEvent\" page");
        return "showEvent";
    }


    @RequestMapping(value = "/showEvent", method = RequestMethod.POST)
    public String subscribeToEvent(@ModelAttribute("id") int eventId) {
        LOGGER.info("Request of \"/showEvent\" page GET");
        Event event = eventService.getEvent(eventId);
        User user =securityService.findLoggedInUsername();
        List<User> participants=event.getParticipants();
        if (userService.isUserParticipant(event,user)) {
            participants.remove(user);
        }else {
            participants.add(user);
        }
        event.setParticipants(participants);
        List<Notification> finalNotifications = eventService.notificationCreator(event);
        eventService.updateEvent(event);


        LOGGER.info("Opening of \"/index\" page");

        notificationService.saveAll(finalNotifications);
        notificationService.sendToAllParticipants(event.getParticipants(), event);
        LOGGER.info("Opening of \"/showEvent\" page");
        return "index";
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
    public String editEvent(Model model,
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LOGGER.info("Request of \"/editEvent\" page POST");
        List<User> participants=userService.parseStringToUsersList(participantsList);
        Event event = eventService.getEvent(id);
        event.setTitle(title);
        event.setEventType(eventType);
        event.setAuthor( securityService.findLoggedInUsername());
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
        List<Notification> finalNotifications = eventService.notificationCreator(event);
        model.addAttribute("eventForm", event);
        redirectAttributes.addAttribute("eventId", event.getId());

        notificationService.saveAll(finalNotifications);
        notificationService.sendToAllParticipants(event.getParticipants(), event);

        LOGGER.info("Redirect to \"/userPage\" page");
        return "redirect:/userPage";
    }




}
