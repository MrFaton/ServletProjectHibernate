package com.nixsolutions.ponarin.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nixsolutions.ponarin.dao.UserDao;
import com.nixsolutions.ponarin.entity.User;
import com.nixsolutions.ponarin.utils.DaoUtils;
import com.nixsolutions.ponarin.utils.HibernateUtils;
import com.nixsolutions.ponarin.validator.UserValidator;

public class HibernateUserDao implements UserDao {
    private static final Logger logger = LoggerFactory
            .getLogger(HibernateUserDao.class);
    private UserValidator userValidator = new UserValidator();
    private DaoUtils daoUtils = new DaoUtils();

    @Override
    public void create(User user) {
        logger.trace("create " + user);

        userValidator.validate(user);

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
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
    public void update(User user) {
        logger.trace("update " + user);

        userValidator.validate(user);

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
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
    public void remove(User user) {
        logger.trace("remove " + user);

        if (user.getId() == null) {
            throw new IllegalArgumentException("User's id == null");
        }

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
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
    public List<User> findAll() {
        logger.trace("find all users");

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            @SuppressWarnings("unchecked")
            List<User> userList = criteria.list();

            session.getTransaction().commit();
            return userList;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }

    @Override
    public User findByLogin(String login) {
        logger.trace("find user by login = " + login);

        if (login.length() == 0) {
            throw new IllegalArgumentException("Login is empty");
        }

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("login", login));
            User user = (User) criteria.uniqueResult();

            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }

    @Override
    public User findByEmail(String email) {
        logger.trace("find user by email = " + email);

        if (email.length() == 0) {
            throw new IllegalArgumentException("Email is empty");
        }

        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            User user = (User) criteria.uniqueResult();

            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            daoUtils.rollBackTransaction(session);
            throw new RuntimeException(ex);
        } finally {
            daoUtils.closeSession(session);
        }
    }

}
