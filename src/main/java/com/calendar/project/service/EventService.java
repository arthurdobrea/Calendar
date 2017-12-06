package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.model.User;

import java.io.IOException;

import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    void updateEvent(Event editedEvent);

    Event updateEventForRest(Event event, EventResource eventResource);

    void deleteEvent(Event event);

    Event getEvent(int eventId);

    List<Event> getEventsByUser(long userId);

    List<Event> getEventsByAuthor(long authorId);

    List<Event> getEventsByTag(TagType tag);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getEventsByKeyword(String keyword);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

    List<Event> getEventCountByPeriod(String date1, String date2);

    List<Event> getEventsByDate(String date);

    String getEventJson(Event event) throws IOException;

    String getEventsJson(List<Event> events) throws IOException;

    List<Event> getFutureEventsByType(EventType eventType);

    EventType getEventTypeByString(String eventType);

    List<EventType> getEventTypeList();

    List<User> getParticipantsByEvent(int eventId);

    String getColorForEvent(EventType eventType);

    List<Event> getAllEvents();

    void setParticipantsTagsAndAuthor(EventResource eventResource, Event event);

    List<Event> searchEvents(EventType type, TagType tag, Long authorId, Long participantId);

    List<Notification> notificationCreator(Event event);

}
