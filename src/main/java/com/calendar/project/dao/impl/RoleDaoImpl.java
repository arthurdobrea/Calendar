package com.calendar.project.dao.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }
}
