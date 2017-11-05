package com.calendar.project.service;

import com.calendar.project.model.Event;
import java.util.List;

public interface EventService {

    Event getEvent(Long eventId);

    List<Event> getEventsByUser(long id);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getAllEvents();

    void saveEvent(Event event);

    void updateEvent(Event editedEvent);

    void deleteEvent(Event event);

}
