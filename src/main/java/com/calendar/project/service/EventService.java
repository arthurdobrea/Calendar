package com.calendar.project.service;

import com.calendar.project.model.Event;

import java.util.List;

public interface EventService {

    public void saveEvent(Event event);

    public List<Event> getAllEvents();

    public Event getEvent(long eventId);

    public List<Event> getEventsByUser(long id);

}
