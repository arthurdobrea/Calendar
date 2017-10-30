package com.calendar.project.dao;

import com.calendar.project.model.Event;

import java.util.List;

/**
 * Created by mhristiniuc on 10/26/2017.
 */
public interface EventDao {

    public void saveEvent(Event event);

    public List<Event> getAllEvents();

    public Event getEvent(long eventId);

    public List<Event> getEventsByUser(long userId);

    public void deleteEvent(Event event);

    public void updateEvent(Event event);

}
