package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.model.User;
import org.apache.log4j.Logger;
import com.calendar.project.model.*;
import org.hibernate.Hibernate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
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
        List<Event> events = entityManager.createQuery("select distinct e from Event e left join e.author left join fetch e.participants left join fetch e.tags where e.id = :idOfEvent", Event.class)
                .setParameter("idOfEvent", eventId)
                .getResultList();
        System.out.println("SELECT = "+events);
        if (events.size() > 0) {
            Event event = events.get(0);
            LOGGER.info("Return event " + event);
            return event;
        }
        LOGGER.info("Return null event");
        return null;
    }


    @Override
    public List<Event> getEventsByUser(long userId) {
        List<Event> events = entityManager.createQuery("select DISTINCT e FROM Event e " +
                "join e.author left join fetch e.participants p left join fetch e.tags WHERE p.id=:idOfUser", Event.class)
                .setParameter("idOfUser", userId)
                .getResultList();

//        Hibernate.initialize(user.getEvents()); // TODO don't forget testing
        LOGGER.info("Returns a list of events where user with ID = " + userId + " is invited");
//        return user.getEvents();
        return events;
    }

    @Override
    public List<Event> getEventsByAuthor(Long authorId) {
        LOGGER.info("Returns a list of events created by user with id = " + authorId);
        return entityManager.createQuery("select DISTINCT e from Event e join e.author left join fetch e.participants p left join fetch e.tags where e.author.id = :idOfAuthor", Event.class)
                .setParameter("idOfAuthor", authorId)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        LOGGER.info("Returns a list of events for location = " + location);
        return entityManager.createQuery("select DISTINCT e from Event e join e.author left join fetch e.participants p left join fetch e.tags where e.location = :location", Event.class)
                .setParameter("location", location)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByType(EventType type) {
        LOGGER.info("Returns list of events of type = " + type);
        return entityManager.createQuery("select DISTINCT e from Event e join e.author left join fetch e.participants p left join fetch e.tags where e.eventType = :type", Event.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Event> getAllEvents() {
        LOGGER.info("Returns a list with all events");

        return entityManager.createQuery("select distinct e from Event e left join fetch e.participants join fetch e.author left join fetch e.tags order by e.start", Event.class)
                .getResultList();

    }

    @Override
    public List<Event> searchEvents(EventType type, TagType tag, Long authorId, Long participantId) {

        String hql = "select distinct e from Event e left join fetch e.participants p join fetch e.author left join fetch e.tags t";

        boolean needAnd = false;

        if (type != null || tag != null || authorId != null || participantId != null)
            hql = hql + " where ";

        if (type != null) {

            hql = hql + "e.eventType = \'" + type + "\'";
            needAnd = true;
        }

        if (tag != null) {
            if (needAnd)
                hql = hql + " and ";
            hql = hql + "t.tag = \'" + tag + "\'";
            needAnd = true;
        }

        if (authorId != null){

            if (needAnd)
                hql = hql + " and ";

            hql = hql + "e.author.id = \'" + authorId + "\'";
            needAnd = true;
        }

        if (participantId != null){

            if(needAnd)
                hql = hql + " and ";
            hql = hql + "p.id = \'" + participantId + "\'";
            needAnd = true;
        }
        LOGGER.info("hql = " + hql);

        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<Event> getEventsByTag(TagType tag) {
        LOGGER.info("Returns a list with events with tag = " + tag);
        return entityManager.createQuery("select DISTINCT e from Event e left join fetch e.participants join e.author left join fetch e.tags t where t.tag = :tag", Event.class)
                .setParameter("tag", tag)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByKeyword(String keyword) {
        LOGGER.info("Returns a list with events containing keyword = " + keyword);
        return entityManager.createQuery("select DISTINCT e from Event e " +
                                                "left join fetch e.author a " +
                                                "left join fetch e.participants p" +
                                                "left join fetch e.tags t " +
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
                .createQuery("select DISTINCT e FROM Event e left join fetch e.participants join e.author left join fetch e.tags WHERE to_char(e.start,'YYYY-MM-DD')=:dateOfEvent", Event.class)
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
                .createQuery("select distinct e FROM Event e left join fetch e.participants join e.author left join fetch e.tags WHERE e.start >= :firstDate and e.start <= :secondDate", Event.class)
                .setParameter("firstDate", first)
                .setParameter("secondDate", second)
                .getResultList();
        LOGGER.info("Returns a list of events planned for interval between " + firstDate + " and " + secondDate);
        return events;
    }

    @Override
    public List<User> getParticipantsByEvent(int eventId){
        List<User> participantsAtEvent = entityManager.createQuery("select DISTINCT u FROM User u " +
                "left JOIN fetch u.events e WHERE e.id=:idOfEvent", User.class).setParameter("idOfEvent", eventId)
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
