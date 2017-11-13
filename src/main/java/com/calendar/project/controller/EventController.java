package com.calendar.project.controller;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger LOGGER = Logger.getLogger(EventController.class);

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());

        return "events";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String showAllEvents(Model model, String filterByKeyword) {
        model.addAttribute("filterByKeyword", filterByKeyword);

        model.addAttribute("events", eventService.getEventsByKeyword(filterByKeyword));

        return "events";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.GET)
    public String updateEvent(int eventId, Model model){
        model.addAttribute("eventForm", eventService.getEvent(eventId));

        return "updateEvent";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateEvent(@ModelAttribute("eventForm") Event eventForm, Model model) {
        List<User> participans = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participans.add(userDao.getUser(u.getId()));
        }

        eventForm.setParticipants(participans);
        model.addAttribute("eventForm", eventForm);
        eventService.updateEvent(eventForm);

        return "redirect:/userPage";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
    public String deleteEvent(int eventId, Model model){
        model.addAttribute("eventForm", eventService.getEvent(eventId));

        return "deleteEvent";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
    public String deleteEvent(@ModelAttribute("eventForm") Event event) {
        eventService.deleteEvent(event);

        return "redirect:/userPage";
    }


    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public String showEvent(Model model, int eventId){
        Event event = eventService.getEvent(eventId);

        model.addAttribute("eventForm", event);

        return "showEvent";
    }

    @RequestMapping(value = "/getParticipantsByEvent", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<User> getEventInJSON(int eventId){
        List<User> participantsByEvent = eventService.getParticipantsByEvent(eventId);

        return participantsByEvent;
    }
}
