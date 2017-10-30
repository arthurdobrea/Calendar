package com.calendar.project.dao;


import com.calendar.project.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    void save(User user);

    void update(User user);

    List<User> getAll();
    public User getUser(long id);
}
