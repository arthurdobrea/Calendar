package com.calendar.project.service;

import com.calendar.project.model.Event;
import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(long eventId);

    List<Event> getEventsByUser(long id);

}
