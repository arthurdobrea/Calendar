package com.calendar.project.dao.impl;

import com.calendar.project.dao.RoleDao;
import com.calendar.project.model.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getOne(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Transactional
    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role r", Role.class)
                .getResultList();
    }
    public Role findById(Long id) {
        return entityManager.createQuery("from Role r where r.id:=id", Role.class)
                .getSingleResult();
    }

    @Override
    public Long findRoleIdByValue(String roleValue) {
        return entityManager.createQuery("select r.id from Role r where r.name=:roleValue", Long.class)
                .setParameter("roleValue", roleValue)
                .getSingleResult();
    }


}
