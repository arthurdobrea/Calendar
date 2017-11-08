package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.Tag;
import com.calendar.project.model.TagType;

import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    public Event getEvent(long eventId);

    public Event getEventByName(String eventName);

    List<Event> getEventsByUser(long id);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getFutureEventsByType(EventType eventType);

    public List<EventType> getEventTypeList();

    public List<Event> getEventsByTag(String tag);


}
