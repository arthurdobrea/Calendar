package com.calendar.project.service;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.Tag;
import com.calendar.project.model.TagType;
import com.calendar.project.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(int eventId);

    List<Event> getEventsByUser(long userId);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventsByAuthor(long authorId);

    List<User> getParticipantsByEvent(long eventId);

    List<Event> getFutureEventsByType(EventType eventType);

    List<EventType> getEventTypeList();

    List<Event> getEventsByTag(String tag);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getEventsByDate(String date);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

}
