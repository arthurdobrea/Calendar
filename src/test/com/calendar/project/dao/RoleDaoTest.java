package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    private Role role;

    @Before
    public void setUp() {
        role = new Role("ROLE_USER");
    }

    @Test
    public void daoGetRoleByIdTest() throws Exception {
        Assert.assertEquals(role, roleDao.getRole(1L));
    }
}
