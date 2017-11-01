package com.calendar.project.dao;

import com.calendar.project.model.Event;

import java.util.List;
import java.util.Set;

/**
 * Created by mhristiniuc on 10/26/2017.
 */
public interface EventDao {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(long eventId);

    List<Event> getEventsByUser(long userId);
}
