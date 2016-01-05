package com.nixsolutions.ponarin.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nixsolutions.ponarin.utils.HibernateUtils;

public class ShutdownListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        HibernateUtils.shutDownFactory();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // NOP
    }

}
