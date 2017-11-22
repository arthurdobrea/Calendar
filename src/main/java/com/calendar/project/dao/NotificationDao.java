package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.User;

import java.util.List;

public interface NotificationDao {

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    List<Notification> getCheckedEvents(User user);

    List<Notification> getUnchekedEvents(User user);

    void changeState(User user, Notification Notification);
}
