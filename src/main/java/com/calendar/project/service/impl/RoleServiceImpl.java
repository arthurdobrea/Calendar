package com.calendar.project.service.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.model.Role;
import com.calendar.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by icebotari on 10/30/2017.
 */
@Service("userProfileService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Long findRoleIdByValue(String value) {
        return roleDao.findRoleIdByValue(value);
    }


}