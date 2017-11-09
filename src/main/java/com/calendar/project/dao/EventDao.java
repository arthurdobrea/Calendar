package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDao {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(long eventId);

    List<Event> getEventsByUser(long userId);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    List<Event> getEventsByAuthor(long authorId);

}
