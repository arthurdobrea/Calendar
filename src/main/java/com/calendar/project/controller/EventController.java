package com.calendar.project.controller;

import com.calendar.project.dao.UserDao;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import org.springframework.stereotype.Controller;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
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


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());

        return "events";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.GET)
    public String updateEvent(Long eventId, Model model){
        model.addAttribute("eventForm", eventService.getEvent(eventId));

        return "updateEvent";
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateEvent(@ModelAttribute("eventForm") Event eventForm, Model model){
        List<User> participans = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participans.add(userDao.getUser(u.getId()));
        }

        eventForm.setParticipants(participans);
        model.addAttribute("eventForm", eventForm );
        eventService.updateEvent(eventForm);

        return "events";   // redirect to all events
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
    public String deleteEvent(Long eventId, Model model){
        model.addAttribute("event", eventService.getEvent(eventId));

        return "deleteEvent";
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
    public String deleteEvent(@ModelAttribute("event") Event event, Model model){
        eventService.deleteEvent(event);
        model.addAttribute("event",event);

        return "redirect:/events";
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
    public String createEvent(@ModelAttribute("eventForm") Event eventForm, RedirectAttributes redirectAttributes) {
        List<User> participans = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));   // TODO investigate why username is set instead of id
            participans.add(userDao.getUser(u.getId()));
        }

        eventForm.setParticipants(participans);
        String username = securityService.findLoggedInUsername();
        eventForm.setAuthor(userService.findByUsername(username));  // TODO maybe it is better to move to service
        eventService.saveEvent(eventForm);
        redirectAttributes.addAttribute("eventId", eventForm.getId());
        //model.addAttribute("event", eventForm);
        //eventFormForm = eventForm;

        return "redirect:/showEvent";
    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public String showEvent(Model model, Long eventId){
        Event event = eventService.getEvent(eventId);
        model.addAttribute("eventForm", event);

        return "showEvent";
  }

//    @RequestMapping(value = "/myevents", method = RequestMethod.GET)
//    public String showAllEvents(Model model, @ModelAttribute("user") User user) {
//        model.addAttribute("events", eventService.getEventsByUser(user.getId()));
//        return "eventByUser";
//    }

    @RequestMapping(value = "/eventusers", method = RequestMethod.GET)
    public String showAllEvents(Model model, @ModelAttribute("event") Event event) {
        model.addAttribute("events", eventService.getEvent(event.getId()));
        return "usersByEvents";
    }
}
