package com.calendar.project.service.impl;

import com.calendar.project.model.Event;
import com.calendar.project.model.MessageBroadcast;
import com.calendar.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void send(String destination, Event eventForm) {
        template.convertAndSend(destination,new MessageBroadcast("&lt;b&gt;"
                +eventForm.getTitle() + " " + eventForm.getLocation() + "&lt;/b&gt;"));
    }
}
