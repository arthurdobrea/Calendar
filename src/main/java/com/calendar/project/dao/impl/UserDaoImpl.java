package com.calendar.project.dao.impl;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.model.UserRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

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
        entityManager.persist(user);
    }

    @Override
    public void updateRole(Long userId, Long role_id) {
        entityManager.createQuery("update UserRole r set r.role_id=:role_id where r.user_id=:userId")
                .setParameter("role_id", role_id)
                .setParameter("userId", userId)
                .executeUpdate();

    }

    @Override
    public void deleteByUsername(String username) {

    }

}
