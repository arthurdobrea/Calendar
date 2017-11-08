package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventDao {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(int eventId);

    List<Event> getEventsByUser(long userId);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getEventsByDate(String localDate);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

}
