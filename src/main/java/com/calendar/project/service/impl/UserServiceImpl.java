package com.calendar.project.service.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
import com.calendar.project.mail.EmailSender;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getUser(long userId){
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
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (user != null) {
                Hibernate.initialize(user.getRoles());
            }
        }
        return users;
    };

    @Override
    public boolean exists(String username) {
        return userDao.findByUsername(username) != null;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRole(1L));
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
        userDao.deleteByUsername(userDao.findByUsername(username));
    }

    public List<User> getUsersListBySubscriptionByEventType(String subscriptionByEventType){
        return userDao.getUsersBySubscriptionByEventType(subscriptionByEventType);
    }

    @Override
    public List<User> getUsersListBySubscriptionByTagType(String subscriptionByTagType){
        return userDao.getUsersBySubscriptionByTagType(subscriptionByTagType);
    }

    @Override
    public void mailToAllUsers(){
          for (User user:getAllUsers())
              mailToUser(user);
    }

    @Override
    public void mailToUser(User user){
        System.out.println(" enums _ "+user.getSubscriptionByEventTypeAsEnums());
        StringBuilder mailText = new StringBuilder();
        for (EventType eventType: (user.getSubscriptionByEventTypeAsEnums())){
            for (Event event:eventService.getFutureEventsByType(eventType)) {
                if (eventType.equals(event.getEventType())) {
                    mailText.append("Event name: " + event.getTitle());
                    mailText.append("Event description: " + event.getDescription());
                    mailText.append("Event start time: " + event.getStart()+"\n");
                    System.out.println("will send to " + user.getFirstname() + " this " + mailText + "");
                }
            }
        }
        EmailSender.sendTo(user.getEmail(), "subscribe from EventEndava "+ user.getSubscriptionByEventType(), " You were subscribed by" + user.getSubscriptionByEventType() + mailText.toString());
    }
}
