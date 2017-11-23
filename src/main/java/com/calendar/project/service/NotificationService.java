package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;
import java.util.List;

public interface NotificationService {

    void sendToAllParticipantsNotification(String username, Event eventForm);

    void sendToAll(String destination, Event eventForm);

    void sendToAllParticipants(List<User> users, Event eventForm);

    void sendToSpecificUser(List<User> users,Notification notification);

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    Notification getNotification(User user, Event event);

    List<Notification> getUnchekedEvents(User User);

    List<Notification> getChekedEvents(User User);

    void changeState(Notification Notification);

    void delete(Notification notification);

}
