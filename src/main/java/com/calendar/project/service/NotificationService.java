package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;

import java.util.List;

public interface NotificationService {

    void sendToAll(String destination, Event eventForm);

    void sendToAllParticipants(List<User> users, Event eventForm);

    void sendToSpecificUser(String username, Event eventForm);

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    List<Notification> getUnchekedEvents(User User);

    void sendToAllParticipantsNotification(List<User> users,Notification notification);
}
