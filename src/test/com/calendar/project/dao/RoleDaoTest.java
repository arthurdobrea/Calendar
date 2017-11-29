package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    private Role role;
    private User user;

    @Before
    public void setUp() {
        role = new Role();
        role.setId(1l);
        role.setName("ROLE_SUPREME_ADMIN");
        user = new User();
        user.setId(1l);
        user.setUsername("userTest2");
        Set<User> users = new HashSet<>();
        users.add(user);
        role.setUsers(users);
    }

    @Test
    public void testGetRole() throws Exception {
        Assert.assertEquals(role, roleDao.getRole(role.getId()));
    }

    @Test
    public void testFindById() throws Exception {
        Assert.assertEquals(role, roleDao.findById(role.getId()));
    }

    @Test
    public void testFindRoleIdByValue() throws Exception {
        Assert.assertEquals(role.getId(), roleDao.findRoleIdByValue(role.getName()));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Role> allRoles = roleDao.findAll();
        Assert.assertNotNull(allRoles);
    }
}
