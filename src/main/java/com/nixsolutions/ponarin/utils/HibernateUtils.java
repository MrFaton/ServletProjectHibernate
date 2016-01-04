package com.nixsolutions.ponarin.utils;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(HibernateUtils.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/Hibernate.cfg.xml");
            configuration.addProperties(loadProperties());
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("db.properties"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutDownFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
