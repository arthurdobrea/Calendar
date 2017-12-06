package com.calendar.project.model.dto;

import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.EventType;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class EventResource {

    private int id;

    private String title;

    private EventType eventType;

    private User author;

    private String location;

    private String participants;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime start;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime end;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime eventCreated = LocalDateTime.now();

    private boolean allDay;

    private String description;

    private String tags;

    private List<Notification> notifications;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(LocalDateTime eventCreated) {
        this.eventCreated = eventCreated;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}