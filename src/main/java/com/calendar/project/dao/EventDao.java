package com.calendar.project.dao;

import com.calendar.project.model.Event;

import java.util.List;
import java.util.Set;

/**
 * Created by mhristiniuc on 10/26/2017.
 */
public interface EventDao {

    public void saveEvent(Event event);

    public List<Event> getAllEvents();

    public Event getEvent(long eventId);

    public Event getEventByName(String eventName);

    public List<Event> getEventsByUser(long userId);

}
