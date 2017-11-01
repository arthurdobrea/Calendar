package com.calendar.project.service;

import com.calendar.project.model.User;
import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    boolean exists(String username);

    List<User> getAllUsers();

    void update(User editedUser);

    User getUser(long id);

}
