package com.calendar.project.dao.impl;

import com.calendar.project.dao.NotificationDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventsUsers;
import com.calendar.project.model.User;
import com.sun.javafx.event.EventUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NotificationDaoImpl implements NotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(EventsUsers eventsUsers){
        entityManager.merge(eventsUsers);
    }

    @Override
    public List<EventsUsers> getCheckedEvents(User user) {
        return entityManager.createQuery("from EventsUsers e where user.id  = :idOfuser "
                + "and dateChecked != null ", EventsUsers.class)
                .setParameter("idOfuser", user.getId())
                .getResultList();
    }

    @Override
    public List<EventsUsers> getUnchekedEvents(User user) {
        return entityManager.createQuery("from EventsUsers e where user.id  = :idOfuser "
                + "and dateChecked == null ", EventsUsers.class)
                .setParameter("idOfuser", user.getId())
                .getResultList();
    }

    @Override
    public void changeState(User user, EventsUsers event) {

    }

}
