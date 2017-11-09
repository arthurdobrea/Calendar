package com.calendar.project.service;

import com.calendar.project.model.Role;
import java.util.List;


public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Long findRoleIdByValue(String value);
}
