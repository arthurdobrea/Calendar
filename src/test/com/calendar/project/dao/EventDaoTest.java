package com.calendar.project.dao;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.TagType;
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
    private Tag tag;

    @Resource
    private EventDao eventDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        userTest = new User();
        userTest.setId(1L);
        userTest.setFirstname("John");
        userTest.setLastname("Lennon");
        tag = new Tag();
        tag.setTag(TagType.APPLICATION_MANAGEMENT);
        Set<Tag> tags = new HashSet<>();
        event = new Event();
        event.setTitle("Java Presentation");
        event.setEventType(EventType.TRAINING);
        event.setTags(tags);
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
    public void testGetEvent() throws Exception {
        Assert.assertEquals(event, eventDao.getEvent(event.getId()));
    }

    @Test
    public void testEventName() throws Exception {
        Assert.assertEquals("Java Presentation", event.getTitle());
    }

    @Test
    public void testGetEventsByUserNotEmptyList() throws Exception {
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
        Assert.assertFalse(eventsWhereUserInvited.isEmpty());
    }

    @Test
    public void testGetEventsByAuthorDoesFetchAuthor() throws Exception{
        List<Event> eventsOfAuthor = eventDao.getEventsByAuthor(userTest.getId());

        for (Event e : eventsOfAuthor)
            Assert.assertNotNull(e.getAuthor());
    }

    @Test
    public void testGetEventsByAuthorReturnsAuthorsEvents() throws Exception{
        List<Event> eventsOfAuthor = eventDao.getEventsByAuthor(userTest.getId());

        for (Event e : eventsOfAuthor){
            Assert.assertEquals("Event of another author", userTest.getId(), e.getAuthor().getId());
        }
    }

    @Test
    public void testGetEventsByLocationNotEmptyList() throws Exception{
        List<Event> eventsByLocation = eventDao.getEventsByLocation(event.getLocation());
        Assert.assertFalse("List is empty", eventsByLocation.isEmpty());
    }

    @Test
    public void testGetEventsByLocationEmptyList() throws Exception {
        List<Event> eventsByLocation = eventDao.getEventsByLocation("Zimbabwe");
        Assert.assertTrue("List is not empty", eventsByLocation.isEmpty());
    }

    @Test
    public void testGetEventsByTypeNotEmptyList() throws Exception{
        List<Event> eventsByType = eventDao.getEventsByType(event.getEventType());
        Assert.assertFalse("List is empty", eventsByType.isEmpty());
    }


    @Test
    public void testGetAllEventsReturnsValue() throws Exception{
        List<Event> allEvents = eventDao.getAllEvents();
        Assert.assertNotNull(allEvents);
    }

    @Test
    public void testSearchEventsByTypeReturnsEventsWithCorrectType() throws Exception{
       List<Event> eventsByType = eventDao.searchEvents(event.getEventType(), null, null, null);

        for(Event e : eventsByType){
            Assert.assertEquals("Incompatible types", event.getEventType(), e.getEventType());
        }
    }

    @Test
    public void testSearchEventsByTypeReturnsAllEventsWithParticularType() throws Exception{
        List<Event> eventsByType = eventDao.searchEvents(event.getEventType(), null, null, null);
        List<Event> allEvents = eventDao.getAllEvents();
        List<Event> eventsParticularType = new ArrayList<>();
        int countFromEventsByType = 0;
        int countFromAllEvents = 0;

        for(Event e : allEvents){
            if (e.getEventType() == event.getEventType()){
                eventsParticularType.add(e);
                countFromAllEvents++;
            }
        }

        for(int i = 0; i < eventsByType.size(); i++)
            countFromEventsByType++;


        Assert.assertEquals("Not all events with this type were returned", countFromAllEvents, countFromEventsByType);
    }

    @Test
    public void testSearchEventsByTagScenario() throws Exception{
        for (TagType t: event.getEventTagsAsEnum()){
            List<Event> eventsByTag = eventDao.searchEvents(null, t, null, null);
            Assert.assertFalse("List is empty", eventsByTag.isEmpty());
        }
    }

    @Test
    public void testSearchEventsByAuthorScenarioReturnsAsGetEventsByAuthor() throws Exception{
        List<Event> eventsByAuthor = eventDao.searchEvents(null, null, event.getAuthor().getId(), null);
        Assert.assertEquals(eventsByAuthor, eventDao.getEventsByAuthor(event.getAuthor().getId()));
    }

    @Test
    public void testSearchEventsByUserScenario() throws Exception{
        for (User u: event.getParticipants()){
            List<Event> eventsByUser = eventDao.searchEvents(null, null, null, u.getId());
            Assert.assertFalse("List is empty", eventsByUser.isEmpty());
        }
    }

    @Test
    public void testGetEventsByTag() throws Exception{
        List<Event> eventsByTag = eventDao.getEventsByTag(TagType.APPLICATION_MANAGEMENT);
        Assert.assertFalse("List is empty",eventsByTag.isEmpty());
    }

    @Test
    public void testGetEventsByKeywordNotEmptyList() throws Exception {
        List<Event> eventsByKeyword = eventDao.getEventsByKeyword("Hibernate");
        Assert.assertFalse("List is empty", eventsByKeyword.isEmpty());
    }

    @Test
    public void testGetEventsByKeywordEmptyList() throws Exception {
        List<Event> eventsByKeword = eventDao.getEventsByKeyword("&*&^%^$$");
        Assert.assertTrue("List is not empty", eventsByKeword.isEmpty());
    }

    @Test
    public void testSaveEvent() throws Exception {
        eventDao.saveEvent(event);
        Event eventFromDb = eventDao.getEvent(event.getId());
        Assert.assertEquals(event, eventFromDb);
    }

    @Test
    public void testUpdateEvent() throws Exception{
        event.setLocation("Endava Tower, 10th floor");
        eventDao.updateEvent(event);
        Event eventFromDb = eventDao.getEvent(event.getId());

        Assert.assertEquals(event, eventFromDb);
    }

    @Test
    public void testDeleteEvent() throws Exception {
        eventDao.saveEvent(event);
        eventDao.deleteEvent(event);
        Event deletedEvent = eventDao.getEvent(event.getId());
        Assert.assertNull(deletedEvent);
    }


    @Test
    public void testGetEventsByDate() throws Exception {
        List<Event> eventsByDate = eventDao.getEventsByDate("2017-11-27");
        Assert.assertNotNull(eventsByDate);
    }

    @Test
    public void testGetEventsByPeriod() throws Exception{
        List<Event> eventsByPeriod = eventDao.getEventsByPeriod("2017-11-20", "2017-12-01");
        Assert.assertNotNull(eventsByPeriod);
    }

    @Test
    public void testGetParticipantsByEvent() throws Exception{
        List<User> participantsAtEvent = eventDao.getParticipantsByEvent(event.getId());
        Assert.assertFalse(participantsAtEvent.isEmpty());
    }

    @Test
    public void testGetEventsCountByPeriod() throws Exception{
        List<Event> eventCountByPeriod = eventDao.getEventCountByPeriod("2017-11-20", "2017-12-01");
        Assert.assertFalse("List is empty", eventCountByPeriod.isEmpty());
    }

}