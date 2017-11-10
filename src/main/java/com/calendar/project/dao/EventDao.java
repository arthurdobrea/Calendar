package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.TagType;

import java.util.List;

public interface EventDao {

    Event getEvent(Long eventId);

    List<Event> getEventsByUser(Long userId);

    List<Event> getEventsByAuthor(Long authorId);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getAllEvents();

    List<Event> getEventsByTag(TagType tag);

    List<Event> getEventsByKeyword(String keyword);

    void saveEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);

}
