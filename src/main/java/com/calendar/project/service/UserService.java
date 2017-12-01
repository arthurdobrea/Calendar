package com.calendar.project.service;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.UserDTO;
import com.calendar.project.model.dto.UserResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    String getUsersJson(List<User> users) throws IOException;

    String getUserJson(User user) throws IOException;

    User updateUserForRest(User firstUser, User secondUser);

    List<User> parseStringToUsersList(String participants);

    User updateUser(User user, UserResource userResource);

    UserResource updateUserResourceWithUser(UserResource userResource, User user);

    UserResource updateUserResourceWithUserResource(UserResource userResourceToUpdate, UserResource userResource);

    List<User> findLikeFullName(String fullname);

    boolean isUserParticipant(Event event, User user);

    UserDTO updateUserForDTO(UserDTO firstUser, UserDTO secondUser);

    List<Long> parseStringToIntList(String participants);
}
