package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;

import java.util.List;

public interface NotificationDao {

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    Notification getNotification(User user, Event event);

    List<Notification> getCheckedEvents(User user);

    List<Notification> getUncheckedEvents(User user);

    void changeState(Notification Notification);

}
