package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;

public interface EmailService {

    void mailSubscribersNewEvent(Event event);

    public void mailSubscribersAllFutureEvents();

    void mailToUserFutureEvents(User user);

    String makeBodyMailFromEvent(Event event);

    void mailParticipantsNewEvent(Event event);
}
