package com.calendar.project.service;

import com.calendar.project.model.Role;

import java.util.List;

public interface RoleService {

    Role getRole(Long id);

    Role findById(Long id);

    Long findRoleIdByValue(String value);

    List<Role> findAll();

}
