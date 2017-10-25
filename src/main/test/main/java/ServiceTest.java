package main.java;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class ServiceTest {

    @Autowired
    SecurityService securityService;

    public void securityServiceFindLoggedInUsername(){


    }

}
