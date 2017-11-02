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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/create-event", method = RequestMethod.GET)
    public String createEvent(Model model) {
        Event event = new Event();
        List<User> participants = userDao.getAll().stream().collect(Collectors.toList());
        event.setParticipants(participants);
        model.addAttribute("eventForm", event);
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
        User user = securityService.findLoggedInUsername();
        eventForm.setAuthor(userService.findByUsername(user.getUsername()));  // TODO maybe it is better to move to service
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

    @RequestMapping(value = "/participants/{eventName}", method = RequestMethod.GET)
    public String getparticipants (@PathVariable("eventName") String eventName, ModelMap model)  {
        System.out.println("EventName "+eventName+ " "+eventServiceImpl.getEventByName(eventName).getParticipants());
        model.addAttribute("participantsList", eventServiceImpl.getEventByName(eventName).getParticipants());
        return "participants";
    }

}
