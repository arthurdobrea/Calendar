package com.calendar.project.controller;

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

@Controller
public class EventController {

    @Autowired
    EventService eventServiceImpl;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String showAllEvents(Model model) {
        model.addAttribute("events", eventServiceImpl.getAllEvents());
        System.out.println(eventServiceImpl.getAllEvents());
        return "events";
    }

    @RequestMapping(value = "/create-event", method = RequestMethod.GET)
    public String createEvent(Model model) {
        model.addAttribute("eventsList", eventServiceImpl.getEventTypeList());
        model.addAttribute("eventForm", new Event());

        return "createEvent";
    }

    @RequestMapping(value = "/create-event", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("eventForm") Event eventForm) {
        eventServiceImpl.saveEvent(eventForm);
        return "redirect:/index";
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

    @RequestMapping(value = "/participants/{eventName}", method = RequestMethod.GET)
    public String getparticipants (@PathVariable("eventName") String eventName, ModelMap model)  {
        System.out.println("EventName "+eventName+ " "+eventServiceImpl.getEventByName(eventName).getParticipants());
        model.addAttribute("participantsList", eventServiceImpl.getEventByName(eventName).getParticipants());
        return "participants";
    }

}
