package com.calendar.project.service.impl;

import com.calendar.project.config.BASE64DecodedMultipartFile;
import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
import com.calendar.project.mail.EmailSender;
import com.calendar.project.model.Event;
import com.calendar.project.model.dto.UserResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    @Autowired
    private EventService eventService;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getUser(long userId){
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
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    @Override
    public boolean exists(String username) {
        return userDao.findByUsername(username) != null;
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
    @Transactional
    public void deleteUserByUsername(String username) {
        userDao.deleteUser(userDao.findByUsername(username));
    }

    public List<User> getUsersListBySubscriptionByEventType(String subscriptionByEventType){
        return userDao.getUsersBySubscriptionByEventType(subscriptionByEventType);
    }

    @Override
    public List<User> getUsersListBySubscriptionByTagType(String subscriptionByTagType){
        return userDao.getUsersBySubscriptionByTagType(subscriptionByTagType);
    }

    @Override
    public String getUsersJson(List<User> users) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonArray usersJsonArr = new JsonArray();
        for (User user : users) {
            JsonObject userAsJson = new JsonObject();
            userAsJson.addProperty("id", user.getId());
            userAsJson.addProperty("username", user.getUsername());
            userAsJson.addProperty("firstname", user.getFirstname());
            userAsJson.addProperty("lastname", user.getLastname());
            userAsJson.addProperty("email", user.getEmail());
            userAsJson.addProperty("position", user.getPosition());
            if(user.getImage() == null) userAsJson.addProperty("image", "null");
            else{byte[] encodeBase64 = Base64.getEncoder().encode(user.getImage());
                String base64Encoded = new String(encodeBase64, "UTF-8");
                userAsJson.addProperty("image", base64Encoded);}
            userAsJson.addProperty("subscription by event type", user.getSubscriptionByEventType());
            userAsJson.addProperty("subscription by tag type", user.getSubscriptionByTagType());
            userAsJson.addProperty("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()).toString());
            userAsJson.addProperty("invitations", user.getEvents().stream().map(Event::getTitleAndId).collect(Collectors.toList()).toString());
            userAsJson.addProperty("created by author", user.getEventsOfAuthor().stream().map(Event::getTitleAndId).collect(Collectors.toSet()).toString());
            usersJsonArr.add(userAsJson);
        }
        String usersString = usersJsonArr.toString();
        Object json = mapper.readValue(usersString, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public String getUserJson(User user) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonObject userAsJson = new JsonObject();
        JsonArray userJsonArr = new JsonArray();
        userAsJson.addProperty("id", user.getId());
        userAsJson.addProperty("username", user.getUsername());
        userAsJson.addProperty("firstname", user.getFirstname());
        userAsJson.addProperty("lastname", user.getLastname());
        userAsJson.addProperty("email", user.getEmail());
        userAsJson.addProperty("position", user.getPosition());
        if(user.getImage() == null) userAsJson.addProperty("image", "null");
        else{byte[] encodeBase64 = Base64.getEncoder().encode(user.getImage());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        userAsJson.addProperty("image", base64Encoded);}
        userAsJson.addProperty("subscription by event type", user.getSubscriptionByEventType());
        userAsJson.addProperty("subscription by tag type", user.getSubscriptionByTagType());
        userAsJson.addProperty("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()).toString());
        userAsJson.addProperty("invitations", user.getEvents().stream().map(Event::getTitleAndId).collect(Collectors.toList()).toString());
        userAsJson.addProperty("created by author", user.getEventsOfAuthor().stream().map(Event::getTitleAndId).collect(Collectors.toSet()).toString());
        userJsonArr.add(userAsJson);
        String userString = userJsonArr.toString();
        Object json = mapper.readValue(userString, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Override
    public User updateUserForRest(User firstUser, User secondUser){
        firstUser.setFirstname(secondUser.getFirstname());
        firstUser.setLastname(secondUser.getLastname());
        firstUser.setPassword(bCryptPasswordEncoder.encode(secondUser.getPassword()));
        firstUser.setEmail(secondUser.getEmail());
        return firstUser;
    }

    @Override
    public User updateUser(User user, UserResource userResource){
        user.setFirstname(userResource.getFirstname());
        user.setLastname(userResource.getLastname());
        user.setPassword(bCryptPasswordEncoder.encode(userResource.getPassword()));
        user.setConfirmPassword(bCryptPasswordEncoder.encode(userResource.getConfirmPassword()));
        user.setEmail(userResource.getEmail());
        return user;
    }

    @Override
    public UserResource updateUserResourceWithUser(UserResource userResource, User user){
        userResource.setFirstname(user.getFirstname());
        userResource.setLastname(user.getLastname());
        userResource.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userResource.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
        BASE64DecodedMultipartFile base64DecodedMultipartFile = new BASE64DecodedMultipartFile(user.getImage());
        userResource.setMultipartFile(base64DecodedMultipartFile);
        userResource.setEmail(user.getEmail());
        return userResource;
    }

    @Override
    public UserResource updateUserResourceWithUserResource(UserResource userResourceToUpdate, UserResource userResource){
        userResourceToUpdate.setFirstname(userResource.getFirstname());
        userResourceToUpdate.setLastname(userResource.getLastname());
        userResourceToUpdate.setPassword(bCryptPasswordEncoder.encode(userResource.getPassword()));
        userResourceToUpdate.setConfirmPassword(bCryptPasswordEncoder.encode(userResource.getConfirmPassword()));
        userResourceToUpdate.setMultipartFile(userResource.getMultipartFile());
        userResourceToUpdate.setEmail(userResource.getEmail());
        return userResourceToUpdate;
    }


    @Override
    public List<User> parseStringToUsersList(String participants){
        if (participants==null||participants=="") return null;
        String participantsArray[] = null;
        String participantAttributesArray[] = null;
        List <User> users=getAllUsers();
        System.out.println("users="+users);
        List <User> participantsList=new ArrayList<>();
        participantsArray=parsePhraseByComma(participants);
        if (!checkUserList(participants)||participantsArray==null)
            return null;
        for (String participant:participantsArray) {
            participantAttributesArray=parsePhraseInto2Words(participant);
            if (participantAttributesArray==null) break;
            String participantFirstName = participantAttributesArray[0];
            String participantLastName = participantAttributesArray[1];
            for (User user : users) {
                System.out.println("user.getFirstname()="+user.getFirstname()+"participantFirstName="+participantFirstName);
                if (user.getFirstname().equals(participantFirstName) &&
                        user.getLastname().equals(participantLastName)) {
                    participantsList.add(user);
                }
            }
        }
        return participantsList;
    }

    private String[] parsePhraseInto2Words(String phrase){
        String words[] = null;
        try {
            words = phrase.split(" ");
        } catch (Exception e) {
            System.out.println("Fail Parse phrase into 2 words");
            return null;
        }
        if (words.length!=2) return null;
        return words;
    }
    private String[] parsePhraseByComma(String phrase){
        String names[] = null;
        try{
            names = phrase.split(",");
        } catch (Exception e){
            System.out.println("Fail Parse phrase into  names by comma");
            return null;
        }
        return names;
    }

    private boolean checkUserList(String userList){
        if (userList!=null||!userList.equals("")||userList.length()>6
                ||userList.contains(",")||userList.contains(" ")) return true;
        return false;
    }
}
