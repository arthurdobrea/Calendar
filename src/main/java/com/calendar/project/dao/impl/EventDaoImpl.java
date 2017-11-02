package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
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
    @Override
    public Event getEventByName(String eventName) {
        Event event = (Event) entityManager.createQuery("SELECT e FROM Event e WHERE event_name=:event_Name")
                .setParameter("event_Name", eventName).getSingleResult();

        Hibernate.initialize(event.getParticipants());  // TODO need to test
        return event;
    }

    @Override
    public List<Event> getEventsByUser(long userId) {
        return null;
    }

    public List<Event> getEventsByUser(Long userId) {
        User user = (User) entityManager.createQuery("from User u where id = :idOfUser")
                .setParameter("idOfUser", userId).getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing
        return user.getEvents();
    }

    public List<Event> getAllEvents() {
        return entityManager.createQuery("from Event e").getResultList();
    }

//    public List<Event> getAllFutureEvents() {
//        return entityManager.createQuery("from Event e where e.startTime>= :now")
//                .setParameter("now", LocalDateTime.now())
//                .getResultList();
//    }


    @Override
    public Event getEvent(long eventId) {
        return null;
    }
}
