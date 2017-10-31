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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceImplTest { //TODO complete the test.


    private SecurityServiceImpl securityService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;


    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();


    private org.springframework.security.core.userdetails.User springUser ;

    @Mock
    UserDao userDao;

    private Role role;

    private User user;

    @Before
    public void setup() {
        securityService = new SecurityServiceImpl(authenticationManager,userDetailsService);
        userDetailsService = new UserDetailsServiceImpl(userDao);
        user = new User("UserTest12");
        role = new Role();
        role.setName("ROLE_USER");
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");
        user.setRoles(roles);

    }

    @Test
    public void autoLoginTest() throws Exception {
        Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(userDetailsService.loadUserByUsername(user.getUsername())).thenReturn(springUser);
        springUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);

        securityService.autoLogin(springUser.getUsername(),springUser.getPassword());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userFromContext = authentication.getName();

        Assert.assertEquals(userFromContext,user.getUsername());
    }


}