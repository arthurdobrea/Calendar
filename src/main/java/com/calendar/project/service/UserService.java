package com.calendar.project.service;

import com.calendar.project.model.User;
import java.util.List;

public interface UserService {

    User getUser(long id);

    User findById(Long id);

    User findByUsername(String username);

    List<User>  getUsersListBySubscriptionByEventType(String subscriptionByTagType);

    List<User> getUsersListBySubscriptionByTagType(String subscriptionByTagType);

    List<User> findAllUsers();

    List<User> getAllUsers();

    boolean exists(String username);

    void save(User user);

    void update(User editedUser);

    void updateUser(User user);

    void deleteUserByUsername(String username);

}
