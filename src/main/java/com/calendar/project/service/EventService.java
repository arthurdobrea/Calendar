package com.calendar.project.service;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.User;

import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(Long eventId);

    List<Event> getEventsByUser(long userId);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventsByAuthor(long authorId);

    List<User> getParticipantsByEvent(long eventId);


}
