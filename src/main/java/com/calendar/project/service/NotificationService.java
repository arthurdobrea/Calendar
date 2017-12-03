package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;

import java.io.IOException;
import java.util.List;

public interface NotificationService {

    void sendToAllParticipantsNotification(String username, Event eventForm);

    void sendToAll(String destination, Event eventForm);

    void sendToAllParticipants(List<User> users, Event eventForm);

    void sendToSpecificUser(List<User> users,Notification notification);

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    List<Notification> getUncheckedEvents(User User);

    List<Notification> getCheckedEvents(User User);

    void changeState(Notification Notification);

    String getNotificationInJson(Notification notification, User u) throws IOException;
}
