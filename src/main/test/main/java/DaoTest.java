package main.java;

import com.calendar.project.config.*;
import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import org.junit.Assert;
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

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class DaoTest {


    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void daoFindByUserNameTest(){
        User user = new User("UserTest");
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");
        user.setRoles(roles);

        User user2;
        user2 = userDao.findByUsername("UserTest");
        user.setId(user2.getId());
        Assert.assertEquals(user,user2);
    }

    @Test
    public void daoPersistUserTest(){
        User user = new User("UserTest2132323");
        HashSet<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        user.setPassword("$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG");
        user.setFirstname("Qawsed");
        user.setLastname("Qawsed");
        user.setEmail("adamaa14@gmail.com");
        user.setRoles(roles);
        userDao.save(user);

        User user2;
        user2 = userDao.findByUsername("UserTest2132323");
        user.setId(user2.getId());
        Assert.assertEquals(user,user2);

    }

    @Test
    public void daoGetRoleByIdTest(){
        Role role = new Role("ROLE_USER");
        Assert.assertEquals(role,roleDao.getOne(1L));
    }



}
