package com.calendar.project.model;

import com.calendar.project.model.enums.EventType;

import com.calendar.project.model.enums.TagType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_user_id", nullable = false)
    private User author;


    @Column(name = "event_location")
    private String location;

    //@JsonBackReference(value = "child")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants = new ArrayList<>();

    @JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "timebegin")
    private LocalDateTime start;

    @Column(name = "timeend")
    @JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ss HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end;

    @Column(name="all_day")
    private boolean allDay;

    @Column(name = "createdata")
    @JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ss HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventCreated = LocalDateTime.now();

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "event")
    private List<Notification> notifications;

//    @ManyToMany(mappedBy = "events",fetch = FetchType.LAZY)
  @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "events_tags", joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Event(){}

    public Event(String title, EventType eventType, User author, String location,
                 List<User> participants, LocalDateTime start, LocalDateTime end,
                 boolean allDay, LocalDateTime eventCreated, String description, Set<Tag> tags) {
        this.title = title;
        this.eventType = eventType;
        this.author = author;
        this.location = location;
        this.participants = participants;
        this.start = start;
        this.end = end;
        this.allDay = allDay;
        this.eventCreated = eventCreated;
        this.description = description;
        this.tags = tags;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public int getId() {
        return id;
    }

    public String getTitleAndId(){return "{id: " + id + ", title: " + title + "}";}

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

//    public String getEventCreatedTime() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        return eventCreated.format(formatter);
//    }

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

    public List<TagType> getEventTagsAsEnum() {
        //if (tags.isEmpty()||tags==null) return null;
        List<TagType> tagTypes=new ArrayList<>();
        for (Tag tag:getTags()){
            for (TagType tt:TagType.values()){
                if(tag.getTag().equals(tt)){ tagTypes.add(tt); break;}
            }
        }
        return tagTypes;
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

    public String getParticipantsToString() {
        if (participants.isEmpty()||participants==null) return null;
        StringBuilder part=new StringBuilder();
        for (User participant:getParticipants())
            part.append(participant.getFullName().toString()+",");
        return part.toString();
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
        if (!author.equals(event.author)) return false;
        if (!location.equals(event.location)) return false;
        //if (!participants.equals(event.participants)) return false;
        if (!start.equals(event.start)) return false;
        if (!end.equals(event.end)) return false;
        if (!eventCreated.equals(event.eventCreated)) return false;
        return description.equals(event.description);
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
                '}';
    }
}
