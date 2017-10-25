package com.calendar.project.service;

import com.calendar.project.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    boolean exists(String username);

    void update(User editedUser);
}
