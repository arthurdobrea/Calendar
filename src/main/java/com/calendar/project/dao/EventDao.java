package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.Tag;

import com.calendar.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventDao {

    Event getEvent(int eventId);

    List<Event> getEventsByUser(Long userId);

    List<Event> getEventsByAuthor(Long authorId);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getAllEvents();

    List<Event> getEventsByTag(String tag);

    void saveEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);

    List<Event> getEventsByDate(String localDate);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

    List<User> getParticipantsByEvent(int eventId);

}
