package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        System.out.println(eventService.getAllEvents());
        return "events";
    }

    @RequestMapping(value = "/create-event", method = RequestMethod.GET)
    public String createEvent(Model model) {
        model.addAttribute("eventForm", new Event());

        return "create-event";
    }

    @RequestMapping(value = "/create-event", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "create-event";
        }

        eventService.saveEvent(eventForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/myevents", method = RequestMethod.GET)
    public String showAllEvents(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("events", eventService.getEventsByUser(user.getId()));
        return "eventByUser";
    }

    @RequestMapping(value = "/eventusers", method = RequestMethod.GET)
    public String showAllEvents(Model model, @ModelAttribute("event") Event event) {
        model.addAttribute("events", eventService.getEvent(event.getId()));
        return "usersByEvents";
    }
}
