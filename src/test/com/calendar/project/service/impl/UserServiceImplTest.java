package com.calendar.project.service.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
import com.calendar.project.model.User;
import com.calendar.project.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private User user;

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private BCryptPasswordEncoder pe;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userDao, roleDao, pe);
        user = new User("UserTest");
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");
    }

    @Test
    public void saveTest() throws Exception {
//        Mockito.when(userDao.save(user)).thenReturn(user);
//        userDao.save(user);
    }

    @Test
    public void existTest() throws Exception {
        Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
        Assert.assertEquals(true, userService.exists(user.getUsername()));
    }

    @Test
    public void findByUsernameTest() throws Exception {
        Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);

        Assert.assertEquals(userService.findByUsername(user.getUsername()), user);

        Mockito.verify(userDao, Mockito.times(1)).findByUsername(user.getUsername());
    }

    @Test
    public void updateUserTest() throws Exception {
        Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);

        user.setFirstname("Firstname");
        user.setLastname("Lastname");
        user.setEmail("email@mail");
        userDao.update(user);

        Assert.assertEquals(userService.findByUsername(user.getUsername()), user);
    }
}