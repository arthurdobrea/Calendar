package com.calendar.project.service.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Event;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.UserDTO;
import com.calendar.project.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    private final RoleDao roleDao;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRole(4L));
        user.setRoles(roles);

        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User editedUser) {
        userDao.update(editedUser);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User entity = userDao.findById(user.getId());
        userDao.update(user);
    }

    @Override
    public User updateUserWithUserDTO(User user, UserDTO userDTO) {
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPosition(userDTO.getPosition());

        if (userDTO.getImage() != null) {
            user.setImage(org.apache.commons.codec.binary.Base64.decodeBase64(userDTO.getImage()));
        }

        return user;
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        userDao.deleteByUsername(userDao.findByUsername(username));
    }

    @Override
    public User getUser(long userId) {
        User user = new User();

        Hibernate.initialize(user.getRoles());
        Hibernate.initialize(user.getEvents());

        return userDao.getUser(userId);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findLikeFullName(String fullname) {
        return userDao.findLikeFullName(fullname);
    }

    @Override
    public List<User> getUsersListBySubscriptionByEventType(String subscriptionByEventType) {
        return userDao.getUsersBySubscriptionByEventType(subscriptionByEventType);
    }

    @Override
    public List<User> getUsersListBySubscriptionByTagType(String subscriptionByTagType) {
        return userDao.getUsersBySubscriptionByTagType(subscriptionByTagType);
    }

    @Override
    public String getUserJson(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray userJsonArr = new JsonArray();

        userJsonArr.add(setPropertiesForUser(user));

        String userString = userJsonArr.toString();
        Object json = mapper.readValue(userString, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public String getUsersJson(List<User> users) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonArray usersJsonArr = new JsonArray();

        for (User user : users) {
            usersJsonArr.add(setPropertiesForUser(user));
        }

        String usersString = usersJsonArr.toString();
        Object json = mapper.readValue(usersString, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public List<User> parseStringToUsersList(String participants) {
        if (participants == null || participants == "") {
            return null;
        }

        String participantsArray[];
        String participantAttributesArray[];
        List<User> users = getAllUsers();
        List<User> participantsList = new ArrayList<>();

        participantsArray = parsePhraseByComma(participants.trim());

        if (!checkUserList(participants) || participantsArray == null) {
            return null;
        }

        for (String participant : participantsArray) {
            participantAttributesArray = parsePhraseInto2Words(participant);

            if (participantAttributesArray == null) {
                break;
            }

            String participantFirstName = participantAttributesArray[0].trim();
            String participantLastName = participantAttributesArray[1].trim();

            for (User user : users) {
                if (user.getFirstname().equals(participantFirstName) &&
                        user.getLastname().equals(participantLastName) && !participantsList.contains(user)) {
                    participantsList.add(user);
                }
            }
        }

        System.out.println("Parse list consist - " + participantsList);

        return participantsList;
    }

    @Override
    public List<Long> parseStringToIntList(String participants) {
        participants = participants.replace("[", "").replace("]", "");
        String[] split = participants.split(",");
        List<String> list = Arrays.asList(split);
        List<Long> intList = new ArrayList<>();

        for (String symbol : list) {
            intList.add(Long.valueOf(symbol));
        }

        return intList;
    }

    @Override
    public Set<User> parseIntegerListToUserList(List<Long> intList) {
        Set<User> participantList = new HashSet<>();

        for (Long l : intList) {
            participantList.add(findById(l));
        }

        return participantList;
    }

    @Override
    public boolean exists(String username) {
        return userDao.findByUsername(username) != null;
    }

    @Override
    public boolean isUserParticipant(Event event, User user) {
        for (User u : event.getParticipants()) {
            if (u.getId() == user.getId()) {
                return true;
            }
        }

        return false;
    }

    private JsonObject getEventsInfoForUser(Event event) {
        JsonObject eventAsJson = new JsonObject();

        eventAsJson.addProperty("id", event.getId());
        eventAsJson.addProperty("title", event.getTitle());
        eventAsJson.addProperty("eventType", event.getEventType().toString());
        eventAsJson.addProperty("start", event.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return eventAsJson;
    }

    private JsonObject setPropertiesForUser(User user) throws UnsupportedEncodingException {
        JsonObject userAsJson = new JsonObject();

        userAsJson.addProperty("id", user.getId());
        userAsJson.addProperty("username", user.getUsername());
        userAsJson.addProperty("firstname", user.getFirstname());
        userAsJson.addProperty("lastname", user.getLastname());
        userAsJson.addProperty("email", user.getEmail());
        userAsJson.addProperty("position", user.getPosition());

        if (user.getImage() == null) {
            userAsJson.addProperty("image", "null");
        } else {
            byte[] encodeBase64 = Base64.getEncoder().encode(user.getImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");

            userAsJson.addProperty("image", base64Encoded);
        }
        userAsJson.addProperty("subscription_by_event_type", user.getSubscriptionByEventType());
        userAsJson.addProperty("subscription_by_tag_type", user.getSubscriptionByTagType());
        userAsJson.addProperty("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()).toString());

        JsonArray eventArray = new JsonArray();
        for (Event event : user.getEvents()) {
            JsonObject eventsJson = getEventsInfoForUser(event);

            eventArray.add(eventsJson);
        }

        userAsJson.add("events", eventArray);

        JsonArray eventAuthorArray = new JsonArray();
        for (Event event : user.getEventsOfAuthor()) {
            JsonObject eventsOfAuthorJson = getEventsInfoForUser(event);

            eventAuthorArray.add(eventsOfAuthorJson);
        }

        userAsJson.add("eventsOfAuthor", eventAuthorArray);

        return userAsJson;
    }

    private String[] parsePhraseInto2Words(String phrase) {
        String words[] = null;

        try {
            words = phrase.split(" ");
        } catch (Exception e) {
            System.out.println("Fail Parse phrase into 2 words");
            return null;
        }
        if (words.length != 2) return null;
        return words;
    }

    private String[] parsePhraseByComma(String phrase) {
        String names[];

        try {
            names = phrase.split(",");
        } catch (Exception e) {
            System.out.println("Fail Parse phrase into  names by comma");

            return null;
        }

        return names;
    }

    private boolean checkUserList(String userList) {
        if (userList != null || !userList.equals("") || userList.length() > 6
                || userList.contains(",") || userList.contains(" ")) {
            return true;
        }

        System.out.println("Too short user list");

        return false;
    }
}
