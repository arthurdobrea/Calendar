package com.calendar.project.dao;

import com.calendar.project.model.Event;
import java.util.List;

public interface EventDao {

    Event getEvent(Long eventId);

    List<Event> getEventsByUser(Long userId);

    List<Event> getEventsByAuthor(Long authorId);

    List<Event> getAllEvents();

    void saveEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);

}
