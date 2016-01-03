package com.nixsolutions.ponarin.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    private static final String UNIT = "persistenceManager";
    private static EntityManagerFactory factory = null;

    public static synchronized EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(UNIT);
        }
        return factory;
    }

    public static synchronized EntityManager getMannager() {
        return getFactory().createEntityManager();
    }

    public static synchronized void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
