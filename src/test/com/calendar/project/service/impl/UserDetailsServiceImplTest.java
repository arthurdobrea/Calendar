package com.calendar.project.service.impl;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsService;

    private User user;

    private Role role;

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();



    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDetailsService = new UserDetailsServiceImpl(userDao);
        role = new Role();
        role.setName("ROLE_USER");
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        user = new User("UserTest");
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");

    }

    @Test
    public void loadUserByUsername() throws Exception {
        Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);

        Assert.assertEquals(userDetailsService.loadUserByUsername(user.getUsername()),
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities));
    }

}