package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.UserDTO;

import java.io.IOException;

import java.util.List;
import java.util.Set;

public interface UserService {

    void save(User user);

    void update(User editedUser);

    void updateUser(User user);

    User updateUserWithUserDTO(User user, UserDTO userDTO);

    void deleteUserByUsername(String username);

    User getUser(long id);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findLikeFullName(String fullname);

    List<User>  getUsersListBySubscriptionByEventType(String subscriptionByTagType);

    List<User> getUsersListBySubscriptionByTagType(String subscriptionByTagType);

    String getUserJson(User user) throws IOException;

    String getUsersJson(List<User> users) throws IOException;

    List<User> findAllUsers();

    List<User> getAllUsers();

    List<User> parseStringToUsersList(String participants);

    List<Long> parseStringToIntList(String participants);

    Set<User> parseIntegerListToUserList(List<Long> intList);

    boolean exists(String username);

    boolean isUserParticipant(Event event, User user);

}
