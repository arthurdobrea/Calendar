package com.calendar.project.dao;

import com.calendar.project.config.*;
import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
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
import java.util.HashSet;
import java.util.Set;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class UserDaoTest {

    private User user;

    @Resource
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setup() {
        user = new User("UserTest12");
        Role role = new Role("ROLE_USER");
        role.setId(1L);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");
        user.setRoles(roles);
        user.setEvents(null);
        user.setEventsOfAuthor(null);
        entityManager.persist(user);
    }

    @Test
    public void daoFindByUserNameTest() {
        User userFromDb = userDao.findByUsername(user.getUsername());
        Assert.assertEquals(user, userFromDb);

    }

    @Test
    public void daoPersistUserTest() {


    }

    @Test
    public void daoUpdateUserTest() {
        User user = new User("UserTest");
        user.setFirstname("changed name");
        user.setLastname("changed name 2");
        user.setEmail("changedemail@gmail.com");
        userDao.update(user);

        User user2 = userDao.findByUsername("UserTest");
        Assert.assertEquals(user, user2);
    }
}
