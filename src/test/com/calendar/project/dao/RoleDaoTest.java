package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.dao.RoleDao;
import com.calendar.project.model.Role;

import org.junit.Assert;
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

    @Test
    public void daoGetRoleByIdTest(){
        Role role = new Role("ROLE_USER");
        Assert.assertEquals(role,roleDao.getRole(1L));
    }
}
