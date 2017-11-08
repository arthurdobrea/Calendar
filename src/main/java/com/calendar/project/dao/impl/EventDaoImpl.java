package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
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
        List<Event> events = (List<Event>) entityManager.createQuery("from Event e where id = :idOfEvent")
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

        User user = (User) entityManager.createQuery("from User u where id = :idOfUser")
                .setParameter("idOfUser", userId)
                .getSingleResult();

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
}
