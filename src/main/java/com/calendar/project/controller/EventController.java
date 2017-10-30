package com.calendar.project.controller;


import com.calendar.project.dao.UserDao;
import org.springframework.stereotype.Controller;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class EventController {

    @Autowired
    private static Event eventFormForm;

    @Autowired
    private EventService eventServiceImpl;

    @Autowired
    private UserDao userDao;

    public static Event getEventFormForm() {
        return eventFormForm;
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventServiceImpl.getAllEvents());
//        System.out.println(eventServiceImpl.getAllEvents());
        return "events";
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String showAllEvents(@ModelAttribute("eventForm") Event eventForm, Model model){
        model.addAttribute("eventForm", new Event() );

        eventServiceImpl.updateEvent(eventForm);
        return "updateEvent";
    }


    @RequestMapping(value = "/createEvent", method = RequestMethod.GET)
    public String createEvent(Model model) {
        Event e = new Event();
        List<User> participants = userDao.getAll().stream().collect(Collectors.toList());
        e.setParticipants(participants);
        model.addAttribute("eventForm", e);
        //model.addAttribute("participants", participants);

        return "createEvent";
    }

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String  createEvent(@ModelAttribute("eventForm") Event eventForm, Model model) {
        List<User> participans = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participans.add(userDao.getUser(u.getId()));
        }

        eventForm.setParticipants(participans);
        eventServiceImpl.saveEvent(eventForm);
        model.addAttribute("event", eventForm);
        eventFormForm = eventForm;

        return "redirect:/showEvent";

    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public static String showEvent(Model model){
        model.addAttribute("eventForm", getEventFormForm());

        return "showEvent";

  }


    @RequestMapping(value = "/myevents", method = RequestMethod.GET)
    public String showAllEvents(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("events", eventServiceImpl.getEventsByUser(user.getId()));
        return "eventByUser";
    }

    @RequestMapping(value = "/eventusers", method = RequestMethod.GET)
    public String showAllEvents(Model model, @ModelAttribute("event") Event event) {
        model.addAttribute("events", eventServiceImpl.getEvent(event.getId()));
        return "usersByEvents";
    }

}
