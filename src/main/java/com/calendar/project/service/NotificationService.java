package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;

import java.io.IOException;

import java.util.List;

public interface NotificationService {

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    void sendToAllParticipantsNotification(String username, Event eventForm);

    void sendToAll(String destination, Event eventForm);

    void sendToAllParticipants(List<User> users, Event eventForm);

    void sendToSpecificUser(List<User> users,Notification notification);

    Notification getNotification(User user, Event event);

    List<Notification> getUncheckedEvents(User User);

    List<Notification> getCheckedEvents(User User);

    String getNotificationInJson(Notification notification, User u) throws IOException;

    void changeState(Notification Notification);
}
