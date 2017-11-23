package com.calendar.project.dao;

import com.calendar.project.model.*;
import com.calendar.project.model.Event;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.model.Tag;

import com.calendar.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventDao {

    Event getEvent(int eventId);

    List<Event> getEventsByUser(Long userId);

    List<Event> getEventsByAuthor(Long authorId);

    List<Event> getEventsByLocation(String location);

    List<Event> getEventsByType(EventType type);

    List<Event> getEventsByTag(TagType tag);

    List<Event> getEventsByKeyword(String keyword);

    List<User> getParticipantsByEvent(int eventId);

    List<Event> getAllEvents();

    void saveEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);

    List<Event> getEventsByDate(String localDate);

    List<Event> getEventsByPeriod(String firstDate, String secondDate);

    List<Event> getEventCountByPeriod(String date1, String date2);
}
