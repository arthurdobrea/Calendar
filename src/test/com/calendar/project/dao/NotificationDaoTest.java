package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mhristiniuc on 11/29/2017.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class NotificationDaoTest {

    private Notification notification;
    private User user;
    private Event event;

    @Resource
    private NotificationDao notificationDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        notification = new Notification();
        //notification.setId(1L);
        user = new User();
        user.setId(1L);
        notification.setUser(user);
        event = new Event();
        event.setId(1);
        notification.setEvent(event);
        notification.setViewed(true);

    }

    @Test
    public void testSave() throws Exception {
        notificationDao.save(notification);
        Notification notificationFromDb = notificationDao.getNotification(user,event);
        Assert.assertEquals(notification, notificationFromDb);
    }

    @Test
    public void testSaveAll() throws Exception {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(notification);
        notificationDao.saveAll(notifications);
        List<Notification> notFromDb = new ArrayList<>();

        for (Notification n : notifications){
            notFromDb.add(n);
        }

        Assert.assertNotNull(notFromDb);


    }

    @Test
    public void testGetNotification() throws Exception {

    }

    @Test
    public void testGetCheckedEvents() throws Exception {

    }

    @Test
    public void testGetUnchekedEvents() throws Exception {

    }

    @Test
    public void testChangeState() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}