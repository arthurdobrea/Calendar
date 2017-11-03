package com.calendar.project.service;

import com.calendar.project.model.Role;

import java.util.List;

/**
 * Created by icebotari on 10/30/2017.
 */
public interface RoleService {

    List<Role> findAll();

    public Role findById(Long id);

    Long findRoleIdByValue(String value);
}
