package com.calendar.project.dao;

import com.calendar.project.model.Role;
import java.util.List;

public interface RoleDao {

    Role getRole(Long id);

    Role findById(Long id);

    Long findRoleIdByValue(String roleValue);

    List<Role> findAll();

}