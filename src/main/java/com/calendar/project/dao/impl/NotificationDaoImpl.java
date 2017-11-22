package com.calendar.project.dao.impl;

import com.calendar.project.dao.NotificationDao;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NotificationDaoImpl implements NotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Notification notification) {
        entityManager.persist(notification);
    }

    @Override
    public void saveAll(List<Notification> notifications) {
        notifications.forEach(entityManager::persist);
    }

    @Override
    public List<Notification> getCheckedEvents(User user) {
        return entityManager.createQuery("from Notification e where user.id  = :idOfuser "
                + "and dateChecked != null ", Notification.class)
                .setParameter("idOfuser", user.getId())
                .getResultList();
    }

    @Override
    public List<Notification> getUnchekedEvents(User user) {
        return entityManager.createQuery("from Notification e where user.id  = :idOfuser "
                + "and dateChecked == null ", Notification.class)
                .setParameter("idOfuser", user.getId())
                .getResultList();
    }

    @Override
    public void changeState(User user, Notification Notification) {
//        entityManager.createQuery("update Notification n set isviewed = :state where n.id =:idOfNotification and "+
//                " user.id = :idOfuser",Notification.class)
//                .setParameter("idOfuser", user.getId())
//                .setParameter("state",true);
    }

}
