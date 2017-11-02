package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;

import java.util.List;
import java.util.Set;

/**
 * Created by mhristiniuc on 10/26/2017.
 */
public interface EventDao {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(long eventId);

    Event getEventByName(String eventName);

    List<Event> getEventsByUser(long userId);

    //List<Event> getAllFutureEvents();


}
