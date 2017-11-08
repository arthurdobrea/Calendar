package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(int eventId);

    List<Event> getEventsByUser(long id);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getEventsByDate(String date);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

}
