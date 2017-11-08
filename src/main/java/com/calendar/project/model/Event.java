package com.calendar.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @JsonBackReference(value = "child")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_user_id", nullable = false)
    private User author;

    @Column(name = "event_location")
    private String location;

    @JsonBackReference(value = "child")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;

    @Column(name = "timebegin")
    private String startTime;

    @Column(name = "timeend")
    private String endTime;

    @Column(name = "createdata")
    private LocalDateTime eventCreated = LocalDateTime.now();

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "events",fetch = FetchType.EAGER )
    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    //@JoinTable(name = "events_tags", joinColumns = @JoinColumn(name = "event_id"),
     //       inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Event(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getEventCreated() {
        return eventCreated.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null) return false;
        if (eventType != event.eventType) return false;
        if (author != null ? !author.equals(event.author) : event.author != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null) return false;
        if (participants != null ? !participants.equals(event.participants) : event.participants != null) return false;
        if (startTime != null ? !startTime.equals(event.startTime) : event.startTime != null) return false;
        if (endTime != null ? !endTime.equals(event.endTime) : event.endTime != null) return false;
        if (eventCreated != null ? !eventCreated.equals(event.eventCreated) : event.eventCreated != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (eventCreated != null ? eventCreated.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventType=" + eventType +
                //", author=" + author +
                ", location='" + location + '\'' +
                //", participants=" + participants +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", eventCreated=" + eventCreated +
                ", description='" + description + '\'' +
                '}';
    }
}
