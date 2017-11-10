package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.Tag;
import com.calendar.project.model.TagType;

import java.util.List;

public interface EventService {

    Event getEvent(Long eventId);

    List<Event> getEventsByUser(long id);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getFutureEventsByType(EventType eventType);

    List<EventType> getEventTypeList();

    List<Event> getEventsByTag(TagType tag);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getEventsByKeyword(String keyword);

    List<Event> getAllEvents();

    void saveEvent(Event event);

    void updateEvent(Event editedEvent);

    void deleteEvent(Event event);

}
