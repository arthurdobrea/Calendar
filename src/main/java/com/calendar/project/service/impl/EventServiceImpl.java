package com.calendar.project.service.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    private static final Logger LOGGER = Logger.getLogger(EventServiceImpl.class);

    @Override
    public List<Event> getEventsByUser(long userId) {
        return eventDao.getEventsByUser(userId);
    }

    @Override
    public List<Event> getEventsByAuthor(long authorId){
        return eventDao.getEventsByAuthor(authorId);
    }

    @Override
    public Event getEvent (int id){
            return eventDao.getEvent(id);
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
            String startDate = event.getStart().toString(); // convert to ISO DateTime
            LocalDateTime ldt = LocalDateTime.parse(startDate);

            if (event.getEventType().equals(eventType)&& ldt.isAfter( LocalDateTime.now())) {
                eventList.add(event);
            }
        }

        return eventList;
    }

    @Override
    public List<Event> getEventsByTag(TagType tag){
        return eventDao.getEventsByTag(tag);
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        return eventDao.getEventsByLocation(location);
    }

    @Override
    public List<Event> getEventsByType(EventType type) {
        return eventDao.getEventsByType(type);
    }

    @Override
    public List<Event> getEventsByKeyword(String keyword) {
        return eventDao.getEventsByKeyword(keyword);
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
        event.setTitle(editedEvent.getTitle());
        event.setEventType(editedEvent.getEventType());
        event.setParticipants(editedEvent.getParticipants());
        event.setDescription(editedEvent.getDescription());
        event.setLocation(editedEvent.getLocation());
        event.setStart(editedEvent.getStart());
        event.setEnd(editedEvent.getEnd());
        eventDao.updateEvent(event);
    }

    @Override
    public List<User> getParticipantsByEvent(int eventId){
        return eventDao.getParticipantsByEvent(eventId);
    }
    @Override
    @Transactional(readOnly = false)
    public void deleteEvent(Event eventToDelete) {
        Event event = eventDao.getEvent(eventToDelete.getId());
        eventDao.deleteEvent(event);}

    @Override
    public List<Event> getEventsByDate(String date) {
        return eventDao.getEventsByDate(date);}

    @Override
    public List<Event> getEventsByPeriod(String firstDate, String secondDate){
            return eventDao.getEventsByPeriod(firstDate, secondDate);
    }

    @Override
    public List<Event> getEventCountByPeriod(String date1, String date2){
        return eventDao.getEventCountByPeriod(date1, date2);
    }

    @Override
    public String getEventsJson(List<Event> events) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray eventsJsonArr = new JsonArray();
        for (Event event : events) {
            JsonObject eventAsJson = new JsonObject();
            eventAsJson.addProperty("id", event.getId());
            eventAsJson.addProperty("title", event.getTitle());
            eventAsJson.addProperty("location", event.getLocation());
            eventAsJson.addProperty("Event type", event.getEventType().toString());
            eventAsJson.addProperty("color", getColorForEvent(event.getEventType()));
            eventAsJson.addProperty("start", event.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            eventAsJson.addProperty("end", event.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            eventAsJson.addProperty("allDay", event.isAllDay());
            eventAsJson.addProperty("Created time", event.getEventCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            eventAsJson.addProperty("author", event.getAuthor().getFullName());
            eventAsJson.addProperty("participants", event.getParticipants().stream().map(User::getFullName).collect(Collectors.toSet()).toString());
            eventAsJson.addProperty("description", event.getDescription());
            eventAsJson.addProperty("tags",event.getTags().stream().map(Tag::getTag).collect(Collectors.toSet()).toString());
            eventsJsonArr.add(eventAsJson);
        }
        String eventsString = eventsJsonArr.toString();
        Object json = mapper.readValue(eventsString, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public String getColorForEvent(EventType eventType)
    {
        switch (eventType)
        {
            case MEETING:
                return "#C71585";
            case TRAINING:
                return "#00008B";
            case WORKSHOP:
                return "#FF00FF";
            case STANDUP:
                return "#2E8B57";
            case OFFLINE:
                return "#48D1CC";
            case TEAM_BUILDING:
                return "#FF4500";
            case OTHER:
                return "#F08080";
            default:
                return "#110022";
        }
    }

    @Override
    public Event updateEventForRest(Event event, EventResource eventResource){
        event.setTitle(eventResource.getTitle());
        event.setStart(eventResource.getStart());
        event.setEventType(eventResource.getEventType());
        event.setEnd(eventResource.getEnd());
        event.setLocation(eventResource.getLocation());
        event.setDescription(eventResource.getDescription());
        return event;
    }

    @Override
    public String getEventJson(Event event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray eventsJsonArr = new JsonArray();
            JsonObject eventAsJson = new JsonObject();
            eventAsJson.addProperty("id", event.getId());
            eventAsJson.addProperty("title", event.getTitle());
            eventAsJson.addProperty("location", event.getLocation());
            eventAsJson.addProperty("Event type", event.getEventType().toString());
            eventAsJson.addProperty("start", event.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            eventAsJson.addProperty("end", event.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            eventAsJson.addProperty("allDay", event.isAllDay());
            eventAsJson.addProperty("Created time", event.getEventCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            eventAsJson.addProperty("author", event.getAuthor().getUsername());
            eventAsJson.addProperty("participants", event.getParticipants().stream().map(User::getFullName).collect(Collectors.toSet()).toString());
            eventAsJson.addProperty("description", event.getDescription());
            eventAsJson.addProperty("tags",event.getTags().stream().map(Tag::getTag).collect(Collectors.toSet()).toString());
            eventsJsonArr.add(eventAsJson);
        String eventsString = eventsJsonArr.toString();
        Object json = mapper.readValue(eventsString, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    }
