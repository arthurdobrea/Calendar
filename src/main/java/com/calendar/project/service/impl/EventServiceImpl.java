package com.calendar.project.service.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public Event getEvent(Long id) {
        return eventDao.getEvent(id);
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
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveEvent(Event event){
        eventDao.saveEvent(event);
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

    @Override
    @Transactional(readOnly = false)
    public void deleteEvent(Event eventToDelete){
        Event event = eventDao.getEvent(eventToDelete.getId());

        eventDao.deleteEvent(event);
    }
}
