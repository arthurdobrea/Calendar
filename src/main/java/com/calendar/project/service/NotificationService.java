package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventsUsers;
import com.calendar.project.model.MessageBroadcast;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    void send(String destination,Event eventForm);

    void save(EventsUsers eventsUsers);
}
