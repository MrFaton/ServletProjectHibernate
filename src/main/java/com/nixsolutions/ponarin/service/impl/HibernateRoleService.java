package com.nixsolutions.ponarin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nixsolutions.ponarin.dao.RoleDao;
import com.nixsolutions.ponarin.dao.impl.HibernateRoleDao;
import com.nixsolutions.ponarin.entity.Role;
import com.nixsolutions.ponarin.service.RoleService;

public class HibernateRoleService implements RoleService {
    private static final Logger logger = LoggerFactory
            .getLogger(HibernateRoleService.class);
    private RoleDao roleDao = new HibernateRoleDao();

    @Override
    public void create(Role role) {
        logger.trace("create role");
        roleDao.create(role);
    }

    @Override
    public void update(Role role) {
        logger.trace("update role");
        roleDao.update(role);
    }

    @Override
    public void remove(Role role) {
        logger.trace("remove role");
        roleDao.remove(role);
    }

    @Override
    public Role findByName(String name) {
        logger.trace("find by name: " + name);
        return roleDao.findByName(name);
    }

    @Override
    public Role findById(int id) {
        logger.trace("find by id: " + id);
        HibernateRoleDao hibernateRoleDao = (HibernateRoleDao) roleDao;
        return hibernateRoleDao.findById(id);
    }

}
