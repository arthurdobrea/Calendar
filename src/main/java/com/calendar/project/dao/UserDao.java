package com.calendar.project.dao;

import com.calendar.project.model.User;
import java.util.List;

public interface UserDao {

    User getUser(Long id);

    User findById(Long id);

    User findByUsername(String username);

    List<User> getAll();

    List<User> findAllUsers();

    void save(User user);

    void update(User user);

    void deleteByUsername(User user);

}
