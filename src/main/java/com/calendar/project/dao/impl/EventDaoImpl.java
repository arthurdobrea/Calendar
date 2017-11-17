package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.model.User;
import org.apache.log4j.Logger;
import com.calendar.project.model.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(EventDaoImpl.class);

    @Override
    public Event getEvent(int eventId) {
        LOGGER.info("Returns an event based on its ID");
        List<Event> events = entityManager.createQuery("from Event e where id = :idOfEvent", Event.class)
                .setParameter("idOfEvent", eventId)
                .getResultList();

        if (events.size() > 0) {
            Event event = events.get(0);
            LOGGER.info("Return event " + event);
            return event;
        }
        LOGGER.info("Return null event");
        return null;
    }

    @Override
    public List<Event> getEventsByUser(Long userId) {
        User user = entityManager.createQuery("from User u where id = :idOfUser", User.class)
                .setParameter("idOfUser", userId)
                .getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing
        LOGGER.info("Returns a list of events where user with ID = " + userId + " is invited");
        return user.getEvents();
    }

    @Override
    public List<Event> getEventsByAuthor(Long authorId) {
        LOGGER.info("Returns a list of events created by user with id = " + authorId);
        return entityManager.createQuery("from Event e where e.author.id = :idOfAuthor", Event.class)
                .setParameter("idOfAuthor", authorId)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        LOGGER.info("Returns a list of events for location = " + location);
        return entityManager.createQuery("from Event e where e.location = :location", Event.class)
                .setParameter("location", location)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByType(EventType type) {
        LOGGER.info("Returns list of events of type = " + type);
        return entityManager.createQuery("from Event e where e.eventType = :type", Event.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Event> getAllEvents() {
        LOGGER.info("Returns a list with all events");
        return entityManager.createQuery("from Event e order by e.start", Event.class)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByTag(TagType tag) {
        LOGGER.info("Returns a list with events with tag = " + tag);
        return entityManager.createQuery("select e from Event e join e.tags t where t.tag = :tag", Event.class)
                .setParameter("tag", tag)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByKeyword(String keyword) {
        LOGGER.info("Returns a list with events containing keyword = " + keyword);
        return entityManager.createQuery("select e from Event e " +
                                                "join e.author a " +
                                                "join e.tags t " +
                                                    " where upper(e.title) like :keyword or" +
                                                    " upper(e.description) like :keyword or" +
                                                    " upper(e.location) like :keyword or" +
                                                    " upper(a.username) like :keyword or" +
                                                    " upper(e.eventType) like :keyword or" +
                                                    " upper(t.tag) like :keyword", Event.class)
                .setParameter("keyword", "%" + keyword.toUpperCase() + "%")
                .getResultList();
    }

    @Override
    public void saveEvent(Event event) {
        entityManager.persist(event);
        LOGGER.info("Event " + event + " was saved in DB");
    }

    @Override
    public void updateEvent(Event event) {
        entityManager.merge(event);
        LOGGER.info("Event " + event + " was updated in DB");
    }

    @Override
    public void deleteEvent(Event event) {
        entityManager.remove(event);
        LOGGER.info("Event " + event + " was removed from DB");
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public List<Event> getEventsByDate(String localDate){
        List<Event> events = entityManager
                .createQuery("FROM Event e WHERE to_char(e.start,'YYYY-MM-DD')=:dateOfEvent")
                .setParameter("dateOfEvent", localDate).getResultList();
        LOGGER.info("Returns a list of events planned on " + localDate);
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
        LOGGER.info("Returns a list of events planned for interval between " + firstDate + " and " + secondDate);
        return events;
    }

    @Override
    public List<User> getParticipantsByEvent(int eventId){
        List<User> participantsAtEvent = entityManager.createQuery("SELECT u FROM User u " +
                "JOIN u.events e WHERE e.id=:idOfEvent").setParameter("idOfEvent", eventId)
                .getResultList();
        LOGGER.info("Returns list of users participating at event with ID = " + eventId);
        return participantsAtEvent;
    }

    @Override
    public List<Event> getEventCountByPeriod(String date1, String date2){
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().append(dateTimeFormatter1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        LocalDateTime first = LocalDateTime.parse(date1, dateTimeFormatter);
        LocalDateTime second = LocalDateTime.parse(date2, dateTimeFormatter);
        List<Event> events = entityManager
                .createQuery("select to_char(e.start,'yyyy-MM-dd') AS date, count(e.start) AS number FROM Event e WHERE e.start >= :firstDate and e.start <= :secondDate GROUP BY 1")
                .setParameter("firstDate", first)
                .setParameter("secondDate", second)
                .getResultList();
        return events;
    }

}
