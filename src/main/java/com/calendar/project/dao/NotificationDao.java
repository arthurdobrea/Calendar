package com.calendar.project.dao;

import com.calendar.project.model.Event;
import com.calendar.project.model.EventsUsers;
import com.calendar.project.model.User;

import java.util.List;

public interface NotificationDao {

    void save(EventsUsers eventsUsers);

    List<EventsUsers> getCheckedEvents(User user);

    List<EventsUsers> getUnchekedEvents(User user);

    void changeState(User user, EventsUsers event);


}
