package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventsUsers;
import com.calendar.project.model.User;

import java.util.List;

public interface NotificationDao {

    void save(EventsUsers eventsUsers);

    List<Event> getCheckedEvents(User user);

    List<Event> getUnchekedEvents(User user);

    void changeState(User user, Event event);


}
