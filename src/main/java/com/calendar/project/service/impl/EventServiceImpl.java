package com.calendar.project.service.impl;

import com.calendar.project.model.EventType;
import com.calendar.project.service.EventService;
import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Event getEventByName(String eventName) {
        return eventDao.getEventByName(eventName);
    }

    @Override
    public List<EventType> getEventTypeList(){
        List<EventType> EventTypeList = new ArrayList<>();
        for(EventType eventType : EventType.values()) {
            EventTypeList.add(eventType);
        }
        return EventTypeList;
    }

    @Override
    public List<Event> getFutureEventsByType(EventType eventType) {
        List<Event> eventList = new ArrayList<>();
        for (Event event : getAllEvents()) {
            String startDate = event.getStartTime().replace(' ','T'); // convert to ISO DateTime
            LocalDateTime ldt =LocalDateTime.parse(startDate);
            if (event.getEventType().equals(eventType)&& ldt.isAfter( LocalDateTime.now())) eventList.add(event);
        }
        return eventList;
    }





}
