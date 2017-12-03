package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.UserDTO;
import com.calendar.project.model.dto.UserResource;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    String getUsersJson(List<User> users) throws IOException;

    String getUserJson(User user) throws IOException;

    List<User> parseStringToUsersList(String participants);

    List<User> findLikeFullName(String fullname);

    boolean isUserParticipant(Event event, User user);

    List<Long> parseStringToIntList(String participants);

    Set<User> parseIntegerListToUserList(List<Long> intList);

    User updateUserWithUserDTO(User user, UserDTO userDTO);
}
