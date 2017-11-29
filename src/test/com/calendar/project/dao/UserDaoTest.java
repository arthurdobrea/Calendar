package com.calendar.project.dao;

import com.calendar.project.config.*;
import com.calendar.project.model.Event;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class UserDaoTest {

    private User user;
    private Event event;

    @Resource
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        event = new Event();
        event.setId(1);
        event.setTitle("Spring Security");
        List<Event> events = new ArrayList<>();
        events.add(event);
        user = new User("UserTest12");
        Role role = new Role("ROLE_USER");
        role.setId(10L);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");
        user.setRoles(roles);
        user.setEvents(events);
        user.setEventsOfAuthor(null);
        user.setSubscriptionByEventType("DEVELOPMENT");
        user.setSubscriptionByTagType("NBC");
        entityManager.persist(user);
    }

    @Test
    public void testGetUser() throws Exception {
        Assert.assertEquals(user, userDao.getUser(user.getId()));
    }

    @Test
    public void testFindById() throws Exception {
        Assert.assertEquals(user, userDao.findById(user.getId()));
    }

    @Test
    public void testFindByUserName() throws Exception {
        User userFromDb = userDao.findByUsername(user.getUsername());
        Assert.assertEquals(user, userFromDb);

    }

    @Test
    public void testGetUsersBySubscriptionByEventType() throws Exception {
        List<User> users = userDao.getUsersBySubscriptionByEventType(user.getSubscriptionByEventType());
        Assert.assertNotNull(users);
    }

    @Test
    public void testGetUsersBySubscriptionByTagType() throws Exception {
        List<User> users = userDao.getUsersBySubscriptionByTagType(user.getSubscriptionByTagType());
        Assert.assertNotNull(users);
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> allUsers = userDao.getAll();
        Assert.assertNotNull(allUsers);
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> allUsers = userDao.findAllUsers();
        Assert.assertNotNull(allUsers);
    }

    @Test
    public void testSaveUser() throws Exception {
        userDao.save(user);
        User userFromDb = userDao.findByUsername(user.getUsername());
        Assert.assertEquals(user, userFromDb);
    }

    @Test
    public void testUpdateUser() throws Exception { //TODO check it
        user.setFirstname("changed name");
        user.setLastname("changed name 2");
        user.setEmail("changedemail@gmail.com");
        userDao.update(user);

        User user2 = userDao.findByUsername("UserTest12");
        Assert.assertEquals(user, user2);
    }

    @Test
    public void testDeleteUserByUsername() throws Exception {
        userDao.save(user);
        userDao.deleteByUsername(user);
        User deletedUser = userDao.findByUsername(user.getUsername());
        Assert.assertNull(deletedUser);

    }






}
