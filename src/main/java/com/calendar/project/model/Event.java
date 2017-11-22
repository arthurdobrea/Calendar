package com.calendar.project.model;

import com.calendar.project.model.enums.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "event_name")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    //@JsonBackReference(value = "child")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_user_id", nullable = false)
    private User author;

    @Column(name = "event_location")
    private String location;


    //@JsonBackReference(value = "child")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants = new ArrayList<>();

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "timebegin")
    private LocalDateTime start;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "timeend")
    private LocalDateTime end;

    @Column(name="all_day")
    private boolean allDay;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "createdata")
    private LocalDateTime eventCreated = LocalDateTime.now();

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "events",fetch = FetchType.LAZY )
    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    //@JoinTable(name = "events_tags", joinColumns = @JoinColumn(name = "event_id"),
     //       inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Event(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public LocalDateTime getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(LocalDateTime eventCreated) {
        this.eventCreated = eventCreated;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (allDay != event.allDay) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        if (eventType != event.eventType) return false;
        if (author != null ? !author.equals(event.author) : event.author != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null) return false;
        if (participants != null ? !participants.equals(event.participants) : event.participants != null) return false;
        if (start != null ? !start.equals(event.start) : event.start != null) return false;
        if (end != null ? !end.equals(event.end) : event.end != null) return false;
        if (eventCreated != null ? !eventCreated.equals(event.eventCreated) : event.eventCreated != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        return tags != null ? tags.equals(event.tags) : event.tags == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (allDay ? 1 : 0);
        result = 31 * result + (eventCreated != null ? eventCreated.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title +
                ", eventType=" + eventType +
                ", author=" + author +
                ", location='" + location +
                ", participants=" + participants +
                ", start=" + start +
                ", end=" + end +
                ", allDay=" + allDay +
                ", eventCreated=" + eventCreated +
                ", description='" + description +
                ", tags=" + tags +
                '}';
    }
}
