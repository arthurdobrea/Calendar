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

import javax.persistence.EnumType;
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
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRole(1L));
        user.setRoles(roles);

        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean exists(String username) {
        return userDao.findByUsername(username) != null;
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
    }

    @Override
    @Transactional
    public void update(User editedUser) {
        userDao.update(editedUser);
    }

    @Override
    public void mailToAllUsers(){
          for (User user:getAllUsers())
              mailToUser(user);
    }

    @Override
    public void mailToUser(User user){
        System.out.println(" enums _ "+user.getLabelsAsEnums());
        StringBuilder mailText = new StringBuilder();
        for (EventType eventType:user.getLabelsAsEnums()){
            for (Event event:eventService.getFutureEventsByType(eventType)) {
                if (eventType.equals(event.getEventType())) {
                    mailText.append("Event name: " + event.getEventName());
                    mailText.append("Event description: " + event.getDescription());
                    mailText.append("Event start time: " + event.getStartTime()+"\n");
                    System.out.println("will send to " + user.getFirstname() + " this " + mailText + "");
                }
            }
        }
        EmailSender.sendTo(user.getEmail(), "subscribe from EventEndava "+ user.getLabels(), " You were subscribed by" + user.getLabels() + mailText.toString());
    }
}
