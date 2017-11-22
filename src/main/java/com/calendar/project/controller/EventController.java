package com.calendar.project.controller;

import com.calendar.project.model.*;
import com.calendar.project.service.NotificationService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    private NotificationService notificationService;

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
        List<User> participans = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participans.add(userDao.getUser(u.getId()));
        }

        eventForm.setParticipants(participans);
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
        Event event = new Event();
        List<User> participants = userService.findAllUsers();
        event.setParticipants(participants);
        model.addAttribute("eventForm", event);
        LOGGER.info("Opening of \"/createEvent\" page");
        return "createEvent";
    }

    @MessageMapping("/notification")
    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("eventForm") Event eventForm, RedirectAttributes redirectAttributes) {
        LOGGER.info("Request of \"/createEvent\" page POST");
        List<Notification> notifications = new ArrayList<>();

        List<User> participants = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participants.add(userService.getUser(u.getId()));

            final Notification notification = new Notification(u, eventForm);
            notifications.add(notification);
        }

        eventForm.setParticipants(participants);
        User user = securityService.findLoggedInUsername();
        eventForm.setAuthor(userService.findByUsername(user.getUsername()));  // TODO maybe it is better to move to service
        eventService.saveEvent(eventForm);

        redirectAttributes.addAttribute("eventId", eventForm.getId());

        notificationService.saveAll(notifications);
        notificationService.sendToAllParticipants(participants, eventForm);
        //notificationService.sendToSpecificUser();

        LOGGER.info("Redirect to \"/showEvent\" page");
        return "redirect:/showEvent";
    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public String showEvent(Model model, int eventId) {
        LOGGER.info("Request of \"/showEvent\" page GET");
        Event event = eventService.getEvent(eventId);
//        List<User> participantsByEvent = eventService.getParticipantsByEvent(eventId);
//        event.setParticipants(participantsByEvent);

        Notification notification = notificationService.getNotification(securityService.findLoggedInUsername(), event);
        notificationService.changeState(notification);

        model.addAttribute("eventForm", event);
        LOGGER.info("Opening of \"/showEvent\" page");
        return "showEvent";
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
}
