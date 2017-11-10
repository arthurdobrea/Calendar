package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.TagType;
import com.calendar.project.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event getEvent(Long eventId) {
        List<Event> events = entityManager.createQuery("from Event e where id = :idOfEvent", Event.class)
                .setParameter("idOfEvent", eventId)
                .getResultList();

        if (events.size() > 0) {
            Event event = events.get(0);

            Hibernate.initialize(event.getParticipants());// TODO need to test

            return event;
        }

        return null;
    }

    @Override
    public List<Event> getEventsByUser(Long userId) {

        User user = entityManager.createQuery("from User u where id = :idOfUser", User.class)
                .setParameter("idOfUser", userId)
                .getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing

        return user.getEvents();
    }

    @Override
    public List<Event> getEventsByAuthor(Long authorId) {
        return entityManager.createQuery("from Event e where e.author.id = :idOfAuthor", Event.class)
                .setParameter("idOfAuthor", authorId)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        return entityManager.createQuery("from Event e where e.location = :location", Event.class)
                .setParameter("location", location)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByType(EventType type) {
        return entityManager.createQuery("from Event e where e.eventType = :type", Event.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Event> getAllEvents() {
        return entityManager.createQuery("from Event e", Event.class)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByTag(TagType tag) {
        return entityManager.createQuery("select e from Event e join e.tags t where t.tag = :tag", Event.class)
                .setParameter("tag", tag)
                .getResultList();
    }

    @Override
    public List<Event> getEventsByKeyword(String keyword) {
        return entityManager.createQuery("select e from Event e " +
                                                "join e.author a " +
                                                "join e.tags t " +
                                                    " where upper(e.eventName) like :keyword or" +
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
}
