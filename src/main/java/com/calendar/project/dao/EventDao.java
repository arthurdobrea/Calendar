package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;

import com.calendar.project.model.User;

import java.util.List;

public interface EventDao {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(Long eventId);

    Event getEventByName(String eventName);

    List<Event> getEventsByUser(long userId);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    List<Event> getEventsByAuthor(long authorId);

}
