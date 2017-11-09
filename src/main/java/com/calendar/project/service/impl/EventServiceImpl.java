package com.calendar.project.service.impl;

import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Event> getEventsByAuthor(long authorId){
        return eventDao.getEventsByAuthor(authorId);
    }

    @Override
    public Event getEvent(Long id) {
        return eventDao.getEvent(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteEvent(Event eventToDelete){
        Event event = eventDao.getEvent(eventToDelete.getId());
        eventDao.deleteEvent(event);
    }


    @Override
    @Transactional
    public void updateEvent(Event eventToEdit){
        Event editedEvent = eventDao.getEvent(eventToEdit.getId());
        editedEvent.setEventName(editedEvent.getEventName());
        editedEvent.setEventType(editedEvent.getEventType());
        editedEvent.setParticipants(editedEvent.getParticipants());
        editedEvent.setDescription(editedEvent.getDescription());
        editedEvent.setLocation(editedEvent.getLocation());
        editedEvent.setStartTime(editedEvent.getStartTime());
        editedEvent.setEndTime(editedEvent.getEndTime());
        eventDao.updateEvent(editedEvent);
    }

    @Override
    public List<User> getParticipantsByEvent(long eventId){
        return eventDao.getParticipantsByEvent(eventId);
    }

}
