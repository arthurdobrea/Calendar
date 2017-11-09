package com.calendar.project.dao.impl;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        List<User> users = entityManager.createQuery("from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getResultList();

        return users.stream()
                .findFirst()
                .orElse(null);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        List<User> users = entityManager.createQuery("from User u", User.class)
                .getResultList();
        return users;
    }

    @Override
    public User findById(long id) {
        User user = entityManager.createQuery("from User u where u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return user;
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(long id){
        User user = entityManager.createQuery("FROM User u WHERE u.id=:idOfUser", User.class)
                .setParameter("idOfUser", id).getSingleResult();

        return user;
    }

    @Override
    public void deleteByUsername(User user) {
        entityManager.remove(user);
    }


}
