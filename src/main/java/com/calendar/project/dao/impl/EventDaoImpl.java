package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Event getEvent(long eventId) {
        Event event = (Event) entityManager.createQuery("FROM Event e WHERE id=:idOfEvent")
                .setParameter("idOfEvent", eventId).getSingleResult();

        Hibernate.initialize(event.getParticipants());  // TODO need to test
        return event;
    }

    @Override
    public List<Event> getEventsByUser(long userId) {

        User user = (User) entityManager.createQuery("FROM User u WHERE id=:idOfUser")
                .setParameter("idOfUser", userId).getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing
        return user.getEvents();
    }

    @Override
    public List<Event> getAllEvents(){
        List <Event> result;
        result = entityManager.createQuery("FROM Event e").getResultList();
        return result;
    }

    @Override
    public void saveEvent(Event event) {
        entityManager.persist(event);
    }
}
