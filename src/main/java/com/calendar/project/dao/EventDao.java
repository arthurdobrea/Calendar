package com.calendar.project.dao;

import com.calendar.project.model.Event;
import java.util.List;

public interface EventDao {

    void saveEvent(Event event);

    List<Event> getAllEvents();

    Event getEvent(Long eventId);

    List<Event> getEventsByUser(Long userId);

}
