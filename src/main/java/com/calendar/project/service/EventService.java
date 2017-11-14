package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.enums.EventType;

import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(int eventId);

    List<Event> getEventsByUser(long id);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getFutureEventsByType(EventType eventType);

    List<EventType> getEventTypeList();

    List<Event> getEventsByTag(String tag);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getEventsByDate(String date);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

    List<Event> getEventCountByPeriod(String date1, String date2);
}
