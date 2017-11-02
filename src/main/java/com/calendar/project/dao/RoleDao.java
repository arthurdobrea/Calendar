package com.calendar.project.dao;

import com.calendar.project.model.Role;

public interface RoleDao {

    Role getRole(Long id);
    List<Role> findAll();
    Role findById(Long id);
    Long findRoleIdByValue(String roleValue);


}