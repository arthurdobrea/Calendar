package com.calendar.project.service;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.service.SecurityService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class ServiceSecurityTest {

    @Autowired
    SecurityService securityService;

    public void securityServiceFindLoggedInUsername() {


    }

}
