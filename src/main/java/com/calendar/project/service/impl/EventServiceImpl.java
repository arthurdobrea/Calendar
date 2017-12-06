package com.calendar.project.service.impl;

import com.calendar.project.model.Notification;
import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.EventService;
import com.calendar.project.service.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MobilePushNotificationsService mobilePushNotificationsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private SecurityService securityService;

    @Override
    @Transactional(readOnly = false)
    public void saveEvent(Event event) {
        eventDao.saveEvent(event);
    }

    @Override
    @Transactional
    public void updateEvent(Event editedEvent) {
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
    public Event updateEventForRest(Event event, EventResource eventResource) {
        event.setTitle(eventResource.getTitle());
        event.setStart(eventResource.getStart());
        event.setEventType(eventResource.getEventType());
        event.setEnd(eventResource.getEnd());
        event.setLocation(eventResource.getLocation());
        event.setDescription(eventResource.getDescription());

        return event;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteEvent(Event eventToDelete) {
        Event event = eventDao.getEvent(eventToDelete.getId());
        eventDao.deleteEvent(event);
    }

    @Override
    public Event getEvent(int id) {
        return eventDao.getEvent(id);
    }

    @Override
    public List<Event> getEventsByUser(long userId) {
        return eventDao.getEventsByUser(userId);
    }

    @Override
    public List<Event> getEventsByAuthor(long authorId) {
        return eventDao.getEventsByAuthor(authorId);
    }

    @Override
    public List<Event> getEventsByTag(TagType tag) {
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
    public List<Event> getEventsByPeriod(String firstDate, String secondDate) {
        return eventDao.getEventsByPeriod(firstDate, secondDate);
    }

    @Override
    public List<Event> getEventCountByPeriod(String date1, String date2) {
        return eventDao.getEventCountByPeriod(date1, date2);
    }

    @Override
    public List<Event> getEventsByDate(String date) {
        return eventDao.getEventsByDate(date);
    }

    @Override
    public String getEventJson(Event event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray eventsJsonArr = new JsonArray();

        eventsJsonArr.add(setPropertiesForEvent(event));

        String eventsString = eventsJsonArr.toString();
        Object json = mapper.readValue(eventsString, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public String getEventsJson(List<Event> events) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray eventsJsonArr = new JsonArray();

        for (Event event : events) {
            eventsJsonArr.add(setPropertiesForEvent(event));
        }

        String eventsString = eventsJsonArr.toString();
        Object json = mapper.readValue(eventsString, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public List<Event> getFutureEventsByType(EventType eventType) {
        List<Event> eventList = new ArrayList<>();

        for (Event event : getAllEvents()) {
            if (event.getEventType() != null && event.getStart() != null && eventType != null
                    && event.getEventType().equals(eventType) && event.getStart().isAfter(LocalDateTime.now())) {
                eventList.add(event);
            }
        }

        return eventList;
    }

    @Override
    public EventType getEventTypeByString(String eventType) {
        for (EventType et : EventType.values()) {
            if (et.toString().equals(eventType)) {
                return et;
            }
        }

        return null;
    }

    @Override
    public List<EventType> getEventTypeList() {
        List<EventType> EventTypeList = new ArrayList<>();

        for (EventType eventType : EventType.values()) {
            EventTypeList.add(eventType);
        }

        return EventTypeList;
    }

    @Override
    public List<User> getParticipantsByEvent(int eventId) {
        return eventDao.getParticipantsByEvent(eventId);
    }

    @Override
    public String getColorForEvent(EventType eventType) {
        switch (eventType) {
            case MEETING:
                return "#b61667";
            case TRAINING:
                return "#00897b";
            case WORKSHOP:
                return "#1bb7de";
            case STAND_UP:
                return "#992f99";
            case OFFLINE:
                return "#1a5a8f";
            case TEAM_BUILDING:
                return "#b61616";
            case OTHER:
                return "#13A04C";
            default:
                return "#000000";
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @Override
    public void setParticipantsTagsAndAuthor(EventResource eventResource, Event event) {
        List<Long> participants = userService.parseStringToIntList(eventResource.getParticipants());
        Set<User> users = userService.parseIntegerListToUserList(participants);

        event.setParticipants(new ArrayList<>(users));

        List<Long> tagsForEvent = userService.parseStringToIntList(eventResource.getTags());
        Set<Tag> tags = tagService.parseIntegerListToTagList(tagsForEvent);

        event.setTags(tags);
        event.setAuthor(securityService.findLoggedInUsername());
    }

    @Override
    public List<Event> searchEvents(EventType type, TagType tag, Long authorId, Long participantId) {
        return eventDao.searchEvents(type, tag, authorId, participantId);
    }

    @Override
    public List<Notification> notificationCreator(Event event) {
        List<Notification> notifications = new ArrayList<>();

        for (User u : event.getParticipants()) {
            final Notification notification = new Notification(u, event);

            notifications.add(notification);

            try {
                String notificationString = notificationService.getNotificationInJson(notification, u);
                HttpEntity<String> request = new HttpEntity<>(notificationString);
                CompletableFuture<String> pushNotification = mobilePushNotificationsService.send(request);

                CompletableFuture.allOf(pushNotification).join();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return notifications;
    }

    private JsonObject getUserInfoForEvent(User user) {
        JsonObject eventAsJson = new JsonObject();

        eventAsJson.addProperty("id", user.getId());
        eventAsJson.addProperty("firstname", user.getFirstname());
        eventAsJson.addProperty("lastname", user.getLastname());
        eventAsJson.addProperty("position", user.getPosition());

        return eventAsJson;
    }

    private JsonObject setPropertiesForEvent(Event event) {
        JsonObject eventAsJson = new JsonObject();

        eventAsJson.addProperty("id", event.getId());
        eventAsJson.addProperty("title", event.getTitle());
        eventAsJson.addProperty("location", event.getLocation());
        eventAsJson.addProperty("eventType", event.getEventType().toString());
        eventAsJson.addProperty("color", getColorForEvent(event.getEventType()));
        eventAsJson.addProperty("start", event.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        eventAsJson.addProperty("end", event.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        eventAsJson.addProperty("allDay", event.isAllDay());
        eventAsJson.addProperty("eventCreated", event.getEventCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        JsonObject authorJson = getUserInfoForEvent(event.getAuthor());
        eventAsJson.add("author", authorJson);

        JsonArray userArray = new JsonArray();
        for (User user : event.getParticipants()) {
            JsonObject eventsJson = getUserInfoForEvent(user);

            userArray.add(eventsJson);
        }

        eventAsJson.add("participants", userArray);
        eventAsJson.addProperty("description", event.getDescription());
        eventAsJson.addProperty("tags", event.getTags().stream().map(Tag::getTag).collect(Collectors.toSet()).toString());

        return eventAsJson;
    }
}