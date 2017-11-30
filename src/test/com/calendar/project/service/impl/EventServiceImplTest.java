package com.calendar.project.service.impl;

import com.calendar.project.config.HibernateConfiguration;
import com.calendar.project.dao.EventDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhristiniuc on 11/7/2017.
 */
@Transactional
@ContextConfiguration(classes = HibernateConfiguration.class)
@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    private Event event;
    private User user;
    private Tag tag;

    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    private SecurityService securityService;


    @Mock
    private EventDao eventDao;

    @Before
    public void setUp() {

    }

    @Test
    public void getAllEventsTest() throws Exception {
        List<Event> events = constructEventsList();
        Mockito.when(eventDao.getAllEvents()).thenReturn(events);
        eventService.getAllEvents();
        Mockito.verify(eventDao).getAllEvents();
    }

    @Test
    public void getEvent() throws Exception {
        event = constructEvent();
        Mockito.when(eventDao.getEvent(event.getId())).thenReturn(event);
        eventService.getEvent(event.getId());
        Mockito.verify(eventDao).getEvent(event.getId());
    }

    @Test
    public void saveEventTest() throws Exception {
        event = constructEvent();
        eventService.saveEvent(event);
        Mockito.verify(eventDao).saveEvent(event);
    }

    @Test
    public void deleteEvent() throws Exception{
        event = constructEvent();
        Mockito.when(eventDao.getEvent(event.getId())).thenReturn(event);
        eventService.deleteEvent(event);
        Mockito.verify(eventDao).deleteEvent(event);
    }

    @Test
    public void updateEventTest() throws Exception{
        event = constructEvent();
        Mockito.when(eventDao.getEvent(event.getId())).thenReturn(event);
        eventService.updateEvent(event);
        Mockito.verify(eventDao).updateEvent(event);
    }

    @Test
    public void getEventsByUserTest() throws Exception{
        user = constructUser();
        Mockito.when(eventDao.getEventsByUser(user.getId())).thenReturn(user.getEvents());
        eventService.getEventsByUser(user.getId());
        Mockito.verify(eventDao).getEventsByUser(user.getId());
    }

    @Test
    public void getParticipantsByEvent() throws Exception {
        event = constructEvent();
        Mockito.when(eventDao.getParticipantsByEvent(event.getId())).thenReturn(event.getParticipants());
        eventService.getParticipantsByEvent(event.getId());
        Mockito.verify(eventDao).getParticipantsByEvent(event.getId());
    }

    @Test
    public void testGetEventTypeList() throws Exception {
        List<EventType> eventTypeList = eventService.getEventTypeList();
        Assert.assertNotNull(eventTypeList);
    }

    @Test
    public void testGetFutureEventsByType() throws Exception {
        List<Event> events = eventService.getFutureEventsByType(EventType.MEETING);
        Assert.assertNotNull(events);
    }

    @Test
    public void testGetEventsByTag() throws Exception {
        List<Event> events = constructEventsList();
        Mockito.when(eventDao.getEventsByTag(TagType.APPLICATION_MANAGEMENT)).thenReturn(events);
        eventService.getEventsByTag(TagType.APPLICATION_MANAGEMENT);
        Mockito.verify(eventDao).getEventsByTag(TagType.APPLICATION_MANAGEMENT);
    }

//    @Test
//    public void testGetEventsByTag() throws Exception {
//        List<Event> events = constructEventsList();
//        Mockito.when(eventDao.getEventsByTag(TagType.APPLICATION_MANAGEMENT)).thenReturn(events);
//        eventService.getEventsByTag(TagType.APPLICATION_MANAGEMENT);
//        Mockito.verify(eventDao).getEventsByTag(TagType.APPLICATION_MANAGEMENT);
//    }

    private Event constructEvent() {
        event = new Event();
        event.setId(1);
        event.setTitle("Introduction to Spring MVC");
        event.setEventType(EventType.TRAINING);
        event.setParticipants(constructListOfUsers());
        event.setDescription("First course on Spring MVC");
        event.setLocation("Endava Tower, 2nd floor");
        event.setStart(LocalDateTime.of(2020,12,1,11,00));
        event.setEnd(LocalDateTime.of(2020,12,1,12,00));

        return event;
    }

    private User constructUser(){
        user = new User();
        user.setId(1l);
        user.setUsername("socrates");
        user.setEvents(constructEventsList());
        return user;
    }

    private List<User> constructListOfUsers(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1l);
        user1.setUsername("socrates");
        user1.setEvents(constructEventsList());
        users.add(user1);

        User user2 = new User();
        user2.setId(2l);
        user2.setUsername("platon");
        user2.setEvents(constructEventsList());
        users.add(user2);

        return users;
    }

    private List<Event> constructEventsList() {
        List<Event> allEvents = new ArrayList<>();

        Event event1 = new Event();
        event1.setTitle("Introduction to Spring MVC");
        event1.setEventType(EventType.TRAINING);
        event1.setParticipants(null);
        event1.setDescription("First course on Spring MVC");
        event1.setLocation("Endava Tower, 2nd floor");
        event1.setStart(LocalDateTime.of(2020,12,1,11,00));
        event1.setEnd(LocalDateTime.of(2020,12,1,12,00));
        allEvents.add(event1);

        Event event2 = new Event();
        event2.setTitle("Introduction to Hibernate");
        event2.setEventType(EventType.MEETING);
        event2.setParticipants(null);
        event2.setDescription("First course on Hibernate");
        event2.setLocation("NBC, 4th floor");
        event2.setStart(LocalDateTime.of(2020,12,1,11,00));
        event2.setEnd(LocalDateTime.of(2020,12,1,12,00));
        allEvents.add(event2);

        return allEvents;
    }

//    private List<EventType> constructEventTypeList() {
//        List<EventType> eventTypeList = new ArrayList<>();
//        for(EventType eventType : EventType.values()) {
//            eventTypeList.add(eventType);
//        }
//        return eventTypeList;
//    }
}
