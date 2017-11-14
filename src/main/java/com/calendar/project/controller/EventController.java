package com.calendar.project.controller;

import com.calendar.project.service.TagService;
import org.springframework.http.MediaType;
import com.calendar.project.dao.UserDao;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import org.springframework.stereotype.Controller;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
public class EventController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());

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
}
