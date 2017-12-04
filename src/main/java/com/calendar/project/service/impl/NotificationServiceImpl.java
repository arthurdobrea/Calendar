package com.calendar.project.service.impl;

import com.calendar.project.dao.NotificationDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.MessageBroadcast;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import com.calendar.project.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

        template.convertAndSendToUser(username, "/queue/reply", new MessageBroadcast(eventForm.getStart().format(formatter) +
                " " + eventForm.getTitle()));
    }

    @Override
    public void sendToAll(String destination, Event eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yy");

        template.convertAndSendToUser(destination, "/queue/reply", new MessageBroadcast(eventForm.getStart().format(formatter) +
                " " + eventForm.getTitle()));
    }

    @Override
    public void sendToAllParticipants(List<User> users, Event eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd/yy");

        for (User it : users) {
            template.convertAndSendToUser(it.getUsername(), "/queue/reply", new MessageBroadcast(eventForm.getStart().format(formatter) +
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
    public Notification getNotification(User user, Event event) {
        return notificationDao.getNotification(user, event);
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

    @Override
    public String getNotificationInJson(Notification notification, User u) throws IOException{
        JSONObject body = new JSONObject();
        body.put("to", "/topics/" + u.getId()+".json");
        body.put("priority", "high");
        JSONObject notification2 = new JSONObject();
        notification2.put("title", "Event Manager Notification");
        notification2.put("body", notification.getEvent().getTitle());
        JSONObject data = new JSONObject();
        data.put("event_id", notification.getEvent().getId());
        data.put("title", notification.getEvent().getTitle());
        data.put("eventCreated", notification.getEvent().getEventCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        data.put("start", notification.getEvent().getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        body.put("notification", notification2);
        body.put("data", data);
        return body.toString();
    }
}
