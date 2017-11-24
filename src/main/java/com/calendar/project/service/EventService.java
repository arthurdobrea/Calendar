package com.calendar.project.service;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.model.User;

import java.io.IOException;
import java.util.List;

public interface EventService {

    Event getEvent(int eventId);

    List<Event> getEventsByUser(long userId);

    List<Event> getEventsByAuthor(long authorId);

    List<User> getParticipantsByEvent(int eventId);

    List<Event> getFutureEventsByType(EventType eventType);

    List<EventType> getEventTypeList();

    List<Event> getEventsByTag(TagType tag);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getEventsByKeyword(String keyword);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

    List<Event> getEventsByDate(String date);

    List<Event> getAllEvents();

    void saveEvent(Event event);

    void deleteEvent(Event event);

    void updateEvent(Event editedEvent);

    List<Event> getEventCountByPeriod(String date1, String date2);

    String getEventsJson(List<Event> events) throws IOException;

    Event updateEventForRest(Event event, EventResource eventResource);

    String getEventJson(Event event) throws IOException;

    String getColorForEvent(EventType eventType);
}
