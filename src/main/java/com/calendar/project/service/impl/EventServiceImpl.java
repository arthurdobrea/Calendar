package com.calendar.project.service.impl;

import com.calendar.project.service.EventService;
import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mhristiniuc on 10/25/2017.
 */

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
        List<Event> result = eventDao.getEventsByUser(userId);
        return result;
    }

    @Override
    public Event getEvent(long eventId) {
        return eventDao.getEvent(eventId);
    }

    @Override
    @Transactional
    public void deleteEvent(Event eventToDelete){
        Event event = eventDao.getEvent(eventToDelete.getId());
        eventDao.deleteEvent(event);
    }

    @Override
    @Transactional
    public void updateEvent(Event editedEvent){
        Event event = eventDao.getEvent(editedEvent.getId());
        event.setEventName(editedEvent.getEventName());
        event.setEventType(editedEvent.getEventType());
        event.setParticipants(editedEvent.getParticipants());
        event.setDescription(editedEvent.getDescription());
        event.setLocation(editedEvent.getLocation());
        event.setStartTime(editedEvent.getStartTime());
        event.setEndTime(editedEvent.getEndTime());
    }






}
