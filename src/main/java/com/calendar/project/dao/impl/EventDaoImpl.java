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
    public void saveEvent(Event event) {
        entityManager.persist(event);
    }

    public Event getEvent(Long eventId) {
        Event event = (Event) entityManager.createQuery("from Event e where id = :idOfEvent")
                .setParameter("idOfEvent", eventId).getSingleResult();

        Hibernate.initialize(event.getParticipants());  // TODO need to test
        return event;
    }

    public List<Event> getEventsByUser(Long userId) {



        User user = (User) entityManager.createQuery("FROM User u WHERE id=:idOfUser")
                .setParameter("idOfUser", userId).getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing
        return user.getEvents();
    }

    public List<Event> getAllEvents() {
        return entityManager.createQuery("from Event e").getResultList();
    }

    public void deleteEvent(Event event){
        entityManager.remove(event);
    }

    public void updateEvent(Event event){
        entityManager.merge(event);
    }



}
