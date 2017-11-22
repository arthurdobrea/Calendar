package com.calendar.project.model.dto;

import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by mhristiniuc on 11/18/2017.
 */
public class EventResource {
    private int id;
    private String title;
    private EventType eventType;
    private UserResource author;
    private String location;
    private List<User> participants = new ArrayList<>();
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime start;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime end;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime eventCreated = LocalDateTime.now();
    private String description;
    private Set<TagResource> tags;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public UserResource getAuthor() {
        return author;
    }

    public void setAuthor(UserResource author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public LocalDateTime getStart() {
        return start;
        }

    public void setStart(String start) {
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public LocalDateTime getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(LocalDateTime eventCreated) {
        this.eventCreated = eventCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TagResource> getTags() {
        return tags;
    }

    public void setTags(Set<TagResource> tags) {
        this.tags = tags;
    }
}
