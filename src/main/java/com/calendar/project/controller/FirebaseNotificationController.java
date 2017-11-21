package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirebaseNotificationController {

    @Autowired
    EventService eventService;

    @RequestMapping("/notification")
    public Event eventSendController(){
        Event event = eventService.getEvent(1);
        return event;
    }
}
