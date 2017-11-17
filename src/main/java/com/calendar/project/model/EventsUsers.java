package com.calendar.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events_users_date")
public class EventsUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_event")
    private Event event;

    @Column(name = "dateChecked")
    private LocalDateTime dateChecked;

    public EventsUsers(User user, Event event, LocalDateTime dateChecked) {
        this.user = user;
        this.event = event;
        this.dateChecked = dateChecked;
    }

    public EventsUsers(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getdateChecked() {
        return dateChecked;
    }

    public void setLocalDateTime(LocalDateTime dateChecked) {
        this.dateChecked = dateChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsUsers that = (EventsUsers) o;

        if (!user.equals(that.user)) return false;
        if (!event.equals(that.event)) return false;
        return dateChecked != null ? dateChecked.equals(that.dateChecked) : that.dateChecked == null;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + event.hashCode();
        result = 31 * result + (dateChecked != null ? dateChecked.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventsUsers{" +
                "user=" + user +
                ", event=" + event +
                ", localDateTime=" + dateChecked +
                '}';
    }
}
