package com.calendar.project.service;

import com.calendar.project.model.Event;
import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    public Event getEvent(Long eventId);
    public Event getEventByName(String eventName);

    List<Event> getEventsByUser(long id);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventsByAuthor(long authorId);


}
