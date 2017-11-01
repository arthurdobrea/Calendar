package com.calendar.project.service;

import com.calendar.project.model.Event;

import java.util.List;

/**
 * Created by mhristiniuc on 10/25/2017.
 */
public interface EventService {

    public void saveEvent(Event event);

    public List<Event> getAllEvents();

    public Event getEvent(long eventId);

    public List<Event> getEventsByUser(long id);
}
