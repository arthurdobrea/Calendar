package com.calendar.project.controller;

import com.calendar.project.config.HibernateConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfiguration.class)
public class UserControllerTest {

    // one test as example for controller
    // example of test for controller with parameters here:
    // http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-mvc-unit-testing/
    // to do rest of tests if have time

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void welcomeTest() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        mockMvc.perform(get("/welcome"))
                .andExpect(view().name("welcome"));
    }
}
