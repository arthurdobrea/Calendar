package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventsUsers;
import com.calendar.project.model.MessageBroadcast;
import com.calendar.project.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    void sendToAll(String destination,Event eventForm);

    void save(EventsUsers eventsUsers);

    List<EventsUsers> getUnchekedEvents(User User);


}
