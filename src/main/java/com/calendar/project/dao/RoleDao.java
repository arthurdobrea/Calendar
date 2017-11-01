package com.calendar.project.dao;


import com.calendar.project.model.Role;

import java.util.List;

public interface RoleDao{

    Role getOne(Long id);

    List<Role> findAll();
    Role findById(Long id);
    Long findRoleIdByValue(String roleValue);


}