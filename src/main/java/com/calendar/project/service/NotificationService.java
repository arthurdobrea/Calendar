package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.MessageBroadcast;

public interface NotificationService {

    void send(String destination,Event eventForm);
}
