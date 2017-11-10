package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.swing.text.DateFormatter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event getEvent(int eventId) {
        List<Event> events = (List<Event>) entityManager.createQuery("from Event e where id = :idOfEvent")
                .setParameter("idOfEvent", eventId)
                .getResultList();

        if (events.size() > 0) {
            Event event = events.get(0);
            return event;
        }

        return null;
    }

    @Override
    public List<Event> getEventsByUser(Long userId) {

        User user = (User) entityManager.createQuery("FROM User u WHERE id=:idOfUser")
                .setParameter("idOfUser", userId).getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing

        return user.getEvents();
    }

    @Override
    public List<Event> getEventsByAuthor(Long authorId) {
        return entityManager.createQuery("from Event e where e.author.id = :idOfAuthor")
                .setParameter("idOfAuthor", authorId)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        return entityManager.createQuery("from Event e where e.location = :location")
                .setParameter("location", location)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByType(EventType type) {
        return entityManager.createQuery("from Event e where e.eventType = :type")
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Event> getAllEvents() {
        return entityManager.createQuery("from Event e")
                .getResultList();
    }

    // doesn't work
    @Override
    public List<Event> getEventsByTag(String tag) {
        return null;
    }

    @Override
    public void saveEvent(Event event) {
        entityManager.persist(event);
    }

    @Override
    public void updateEvent(Event event) {
        entityManager.merge(event);
    }

    @Override
    public void deleteEvent(Event event) {
        entityManager.remove(event);
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public List<Event> getEventsByDate(String localDate){
        List<Event> events = entityManager
                .createQuery("FROM Event e WHERE to_char(e.start,'YYYY-MM-DD')=:dateOfEvent")
                .setParameter("dateOfEvent", localDate).getResultList();
        return events;
    }

    @Override
    public List<Event> getEventsByPeriod(String firstDate, String secondDate){
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().append(dateTimeFormatter1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        LocalDateTime first = LocalDateTime.parse(firstDate, dateTimeFormatter);
        LocalDateTime second = LocalDateTime.parse(secondDate, dateTimeFormatter);
        List<Event> events = entityManager
                .createQuery("FROM Event e WHERE e.start >= :firstDate and e.start <= :secondDate")
                .setParameter("firstDate", first)
                .setParameter("secondDate", second)
                .getResultList();
        return events;
    }

    @Override
    public List<User> getParticipantsByEvent(int eventId){
        List<User> participantsAtEvent = entityManager.createQuery("SELECT u FROM User u " +
                "JOIN u.events e WHERE e.id=:idOfUser").setParameter("idOfUser", eventId)
                .getResultList();

        return participantsAtEvent;

    }

}
