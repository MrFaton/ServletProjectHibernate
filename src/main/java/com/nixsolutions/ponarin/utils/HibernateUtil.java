package com.nixsolutions.ponarin.utils;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
//            return configuration.configure("/Hibernate.cfg.xml")
//                    .buildSessionFactory();
            configuration.configure("/Hibernate.cfg.xml");
            
            Properties properties = new Properties();
            try {
                properties.load(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("db.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            configuration.addProperties(properties);
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
