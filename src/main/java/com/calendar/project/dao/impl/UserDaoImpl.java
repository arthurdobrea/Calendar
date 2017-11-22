package com.calendar.project.dao.impl;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User getUser(Long id){
        return entityManager.createQuery("select DISTINCT u from User u left join fetch u.roles left join fetch u.events where u.id = :idOfUser", User.class)
                .setParameter("idOfUser", id)
                .getSingleResult();
    }

    @Override
    public User findById(Long id) {
        return entityManager.createQuery("select DISTINCT u from User u left join fetch u.roles left join fetch u.events where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = entityManager.createQuery("select DISTINCT u from User u left join fetch u.roles left join fetch u.events where u.username=:username", User.class)
                .setParameter("username", username)
                .getResultList();

        return users.stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersBySubscriptionByEventType(String subscriptionByEventType) {
        List<User> users = entityManager.createQuery("select DISTINCT u from User u where u.subscriptionByEventType Like :eventtype", User.class)
                .setParameter("eventtype", "%" + subscriptionByEventType + "%")
                .getResultList();
        return users;
    }

    @Override
    public List<User> getUsersBySubscriptionByTagType(String subscriptionByTagType) {
        List<User> users = entityManager.createQuery("select DISTINCT u from User u where u.subscriptionByTagType Like :tagtype", User.class)
                .setParameter("tagtype", "%" + subscriptionByTagType + "%")
                .getResultList();
        return users;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select distinct u from User u left join fetch u.roles left join fetch u.events join u.eventsOfAuthor", User.class)
                .getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        return entityManager.createQuery("from User u", User.class)
                .getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteByUsername(User user) {
        entityManager.remove(user);
    }

}
