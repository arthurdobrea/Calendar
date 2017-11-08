package com.calendar.project.service;

import com.calendar.project.model.User;
import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    boolean exists(String username);

    List<User> getAllUsers();

    void update(User editedUser);

    void mailToAllUsers();

    void mailToUser(User user);

    User getUser(long id);

    List<User>  getUsersListBySubscriptionByEventType(String subscriptionByTagType);

    List<User> getUsersListBySubscriptionByTagType(String subscriptionByTagType);

}
