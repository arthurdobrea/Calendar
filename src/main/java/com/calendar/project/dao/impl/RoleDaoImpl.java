package com.calendar.project.dao.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.model.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    @Override
    public Role getRole(Long id) {
        LOGGER.info("Return role with id = " + id);
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findById(Long id) {
        LOGGER.info("Return role with id = " + id);
        return entityManager.createQuery("from Role r where r.id: = id", Role.class)
                .getSingleResult();
    }

    @Override
    public Long findRoleIdByValue(String roleValue) {
        LOGGER.info("Return id of a role = " + roleValue);
        return entityManager.createQuery("select r.id from Role r where r.name = :roleValue", Long.class)
                .setParameter("roleValue", roleValue)
                .getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        LOGGER.info("Return a list with all roles");
        return entityManager.createQuery("from Role r", Role.class)
                .getResultList();
    }

}
