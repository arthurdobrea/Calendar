package com.calendar.project.dao;


import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import java.util.List;

import java.util.List;
import java.util.Set;

public interface UserDao {

    User findByUsername(String username);

    void save(User user);

    List<User> getAll();

    List<User> findAllUsers();
    User findById(long id);
    void update(User user);

    void deleteByUsername(User user);
}
