package com.calendar.project.service.impl;

import com.calendar.project.model.EventType;
import com.calendar.project.service.EventService;
import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional(readOnly = false)
    public void saveEvent(Event event){
        eventDao.saveEvent(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @Override
    public List<Event> getEventsByUser(long userId) {
        return eventDao.getEventsByUser(userId);
    }


    public List<Event> getEventsByAuthor(long authorId) {
        return eventDao.getEventsByAuthor(authorId);}


        @Override
        public Event getEvent (int id){
            return eventDao.getEvent(id);
        }

        @Override
        @Transactional
        public void deleteEvent (Event eventToDelete){
            Event event = eventDao.getEvent(eventToDelete.getId());
            eventDao.deleteEvent(event);
        }


        @Override
        @Transactional
        public void updateEvent(Event editedEvent){
            Event event = eventDao.getEvent(editedEvent.getId());
            event.setTitle(editedEvent.getTitle());
            event.setEventType(editedEvent.getEventType());
            event.setParticipants(editedEvent.getParticipants());
            event.setDescription(editedEvent.getDescription());
            event.setLocation(editedEvent.getLocation());
            event.setStart(editedEvent.getStart());
            event.setEnd(editedEvent.getEnd());
        }

    @Override
    public List<Event> getEventsByDate(String date) {
        return eventDao.getEventsByDate(date);}

    @Override
    public List<Event> getEventsByPeriod(String firstDate, String secondDate){
            return eventDao.getEventsByPeriod(firstDate, secondDate);
    }

    }
