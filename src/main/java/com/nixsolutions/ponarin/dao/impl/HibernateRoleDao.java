package com.nixsolutions.ponarin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.nixsolutions.ponarin.dao.RoleDao;
import com.nixsolutions.ponarin.entity.Role;
import com.nixsolutions.ponarin.utils.JPAUtils;

public class HibernateRoleDao implements RoleDao {

    @Override
    public void create(Role role) {
        EntityManager manager = JPAUtils.getMannager();
        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void update(Role role) {
        EntityManager manager = JPAUtils.getMannager();
        manager.getTransaction().begin();
        manager.merge(role);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void remove(Role role) {
        EntityManager manager = JPAUtils.getMannager();
        manager.getTransaction().begin();
        manager.remove(role);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public Role findByName(String name) {
        String query = "SELECT r FROM Role r WHERE NAME=:name";
        EntityManager manager = JPAUtils.getMannager();
        manager.getTransaction().begin();
        Role role = (Role) manager.createQuery(query).setParameter("name", name)
                .getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return role;
    }

}
