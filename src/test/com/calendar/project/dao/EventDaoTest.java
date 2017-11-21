package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Event;
import com.calendar.project.model.enums.EventType;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by mhristiniuc on 11/1/2017.
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
public class EventDaoTest {

    private Event event;
    private User userTest;

    @Resource
    private EventDao eventDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        userTest = new User();
        userTest.setId(1l);
        userTest.setFirstname("John");
        userTest.setLastname("Lennon");
        event = new Event();
        event.setTitle("Java Presentation");
        event.setEventType(EventType.TRAINING);
        event.setAuthor(userTest);
        event.setStart(LocalDateTime.of(2017,10,31, 10,00,00));
        event.setEnd(LocalDateTime.of(2017,10,31, 11,00,00));
        event.setLocation("NBC, 3rd floor");
        event.setEventCreated(LocalDateTime.of(2017, 10, 30, 10, 30, 01));
        event.setDescription("Java Collections");
        List<User> participants = new ArrayList<>();
        participants.add(userTest);
        event.setParticipants(participants);
        entityManager.persist(event);
    }

    @Test
    public void testSaveEvent() throws Exception {
        eventDao.saveEvent(event);
        Event eventFromDb = eventDao.getEvent(event.getId());
        Assert.assertEquals(event, eventFromDb);
    }


    @Test
    public void testGetEvent() throws Exception {
        Assert.assertEquals(event, eventDao.getEvent(event.getId()));
    }

    @Test
    public void testUpdateEvent() throws Exception{
        event.setLocation("Endava Tower, 10th floor");
        eventDao.updateEvent(event);
        Event eventFromDb = eventDao.getEvent(event.getId());

        Assert.assertEquals(event, eventFromDb);
    }

    @Test
    public void testGetEventsByUser() throws Exception {
        Event event1 = new Event();
        event1.setTitle("Java Presentation 2");
        event1.setEventType(EventType.TRAINING);
        event1.setAuthor(userTest);
        event1.setStart(LocalDateTime.of(2017,10,31, 10,00,00));
        event1.setEnd(LocalDateTime.of(2017,10,31, 11,00,00));
        event1.setLocation("NBC, 3rd floor");
        event1.setEventCreated(LocalDateTime.of(2017, 10, 30, 10, 30, 01));
        event1.setDescription("Java Collections 2");
        List<User> participants = new ArrayList<>();
        participants.add(userTest);
        event1.setParticipants(participants);
        entityManager.persist(event1);

        List<Event> eventsWhereUserInvited =  eventDao.getEventsByUser(userTest.getId());
        Assert.assertNotNull(eventsWhereUserInvited);
    }

    @Test
    public void testGetEventsByAuthor() throws Exception{
        List<Event> eventsOfAuthor = eventDao.getEventsByAuthor(userTest.getId());
        Assert.assertNotNull(eventsOfAuthor);
    }

    @Test
    public void testGetAllEventsReturnsValue() throws Exception{
        List<Event> allEvents = eventDao.getAllEvents();
        Assert.assertNotNull(allEvents);
    }

    @Test
    public void testGetParticipantsByEvent() throws Exception{
        List<User> participantsAtEvent = eventDao.getParticipantsByEvent(event.getId());
        Assert.assertNotNull(participantsAtEvent);
    }


    @Test
    public void testDeleteEvent() throws Exception {
        eventDao.saveEvent(event);
        eventDao.deleteEvent(event);
        Event deletedEvent = eventDao.getEvent(event.getId());
        Assert.assertNull(deletedEvent);
    }
}