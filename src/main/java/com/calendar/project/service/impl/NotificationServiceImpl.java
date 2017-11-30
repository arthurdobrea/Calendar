package com.calendar.project.service.impl;

import com.calendar.project.dao.NotificationDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.MessageBroadcast;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import com.calendar.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public void sendToAllParticipantsNotification(String username, Event eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yy");

        template.convertAndSendToUser(username, "/queue/reply", new MessageBroadcast(eventForm.getEventCreated().format(formatter) +
                " " + eventForm.getTitle()));
    }

    @Override
    public void sendToAll(String destination, Event eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yy");

        template.convertAndSendToUser(destination, "/queue/reply", new MessageBroadcast(eventForm.getEventCreated().format(formatter) +
                " " + eventForm.getTitle()));
    }

    @Override
    public void sendToAllParticipants(List<User> users, Event eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yy");

        for (User it : users) {
            template.convertAndSendToUser(it.getUsername(), "/queue/reply", new MessageBroadcast(eventForm.getEventCreated().format(formatter) +
                    " " + eventForm.getTitle()));
        }
    }

    @Override
    public void sendToSpecificUser(List<User> users, Notification notification) {
        for (User it : users) {
            template.convertAndSendToUser(it.getUsername(), "/queue/reply", new MessageBroadcast(
                    notification.getEvent().getTitle()));
        }
    }

    @Override
    @Transactional
    public void save(Notification notification) {
        notificationDao.save(notification);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<Notification> notifications) {
        notificationDao.saveAll(notifications);
    }

    @Override
    public List<Notification> getUncheckedEvents(User user) {
        return notificationDao.getUncheckedEvents(user);
    }

    @Override
    public List<Notification> getCheckedEvents(User user) {
        return notificationDao.getCheckedEvents(user);
    }

    @Override
    @Transactional
    public void changeState(Notification notification) {
        notificationDao.changeState(notification);
    }
}
