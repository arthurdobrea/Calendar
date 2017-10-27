package com.calendar.project.dao;

import com.calendar.project.model.User;

public interface UserDao {

    User findByUsername(String username);

    void save(User user);

    void update(User user);

}
