package com.calendar.project.dao.impl;

import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Event getEvent(Long eventId) {
        List<Event> events = (List<Event>) entityManager.createQuery("FROM Event e WHERE id =:idOfEvent")
                .setParameter("idOfEvent", eventId).getResultList();

        if(events.size() > 0)
        {
            Event event = events.get(0);
           // Hibernate.initialize(event.getParticipants());// TODO need to test
            return event;
        }

        return null;
    }

    @Override
    public List<Event> getEventsByUser(long userId) {

        User user = (User) entityManager.createQuery("FROM User u WHERE id=:idOfUser")
                .setParameter("idOfUser", userId).getSingleResult();

        Hibernate.initialize(user.getEvents()); // TODO don't forget testing
        return user.getEvents();
    }

    @Override
    public List<Event> getAllEvents() {
        return entityManager.createQuery("FROM Event e").getResultList();
    }

    @Override
    public void deleteEvent(Event event){
        entityManager.remove(event);
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void updateEvent(Event event){
        entityManager.merge(event);
    }

    @Override
    public List<Event> getEventsByAuthor(long authorId) {

        List<Event> events = entityManager
                .createQuery("FROM Event e WHERE e.author.id=:idOfAuthor")
                .setParameter("idOfAuthor", authorId).getResultList();

        return events;
    }

    @Override
    public List<User> getParticipantsByEvent(long eventId){
        List<User> participantsAtEvent = entityManager.createQuery("SELECT u FROM User u " +
                "JOIN u.events e WHERE e.id=:idOfUser").setParameter("idOfUser", eventId)
                .getResultList();

        return participantsAtEvent;

    }

}
