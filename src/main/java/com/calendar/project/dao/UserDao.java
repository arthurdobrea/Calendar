package com.calendar.project.dao;
import java.util.List;

import com.calendar.project.model.User;

public interface UserDao{
    User findByUsername(String username);

    void save(User user);

    void update(User user);

    List<User> getAll();
}
