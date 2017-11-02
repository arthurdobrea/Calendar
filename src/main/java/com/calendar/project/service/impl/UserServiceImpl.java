package com.calendar.project.service.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
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
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Transactional
    public void updateUser(User user) {
        User entity = userDao.findById(user.getId());
        if(entity!=null){
            entity.setUsername(entity.getUsername());
            if(!user.getPassword().equals(entity.getPassword())){
                entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            entity.setFirstname(user.getFirstname());
            entity.setLastname(user.getLastname());
            entity.setEmail(user.getEmail());
            for(Role r: user.getRoles()){
                userDao.updateRole(user.getId(), roleDao.findRoleIdByValue(r.getName()));
            }
            userDao.update(entity);
        }
    }
    public User findById(Long id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        userDao.deleteByUsername(userDao.findByUsername(username));
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
}
