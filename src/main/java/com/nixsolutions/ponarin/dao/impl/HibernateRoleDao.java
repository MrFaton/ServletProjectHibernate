package com.nixsolutions.ponarin.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nixsolutions.ponarin.dao.RoleDao;
import com.nixsolutions.ponarin.entity.Role;
import com.nixsolutions.ponarin.utils.DaoUtils;
import com.nixsolutions.ponarin.utils.HibernateUtils;

public class HibernateRoleDao implements RoleDao {
    private static final Logger logger = LoggerFactory
            .getLogger(HibernateRoleDao.class);
    private DaoUtils daoUtils = new DaoUtils();

    @Override
    public void create(Role role) {
        logger.trace("create " + role);

        if (role.getName().length() == 0) {
            throw new IllegalArgumentException("Role's name is empty");
        }

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }

    @Override
    public void update(Role role) {
        logger.trace("update " + role);

        if (role.getName().length() == 0) {
            throw new IllegalArgumentException("Role's name is empty");
        }
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }

    @Override
    public void remove(Role role) {
        logger.trace("remove " + role);

        if (role.getId() == null) {
            throw new IllegalArgumentException("Role's id requiered");
        }

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }

    @Override
    public Role findByName(String name) {
        logger.trace("searching for role by name = " + name);

        if (name.length() == 0) {
            throw new IllegalArgumentException("Role's name is empty");
        }

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", name));
            Role role = (Role) criteria.uniqueResult();

            session.getTransaction().commit();
            return role;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }
}
