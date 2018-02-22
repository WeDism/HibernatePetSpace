package com.pet_space.storages;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class HibernateFactory {
    private static final Logger LOG = getLogger(HibernateFactory.class);
    private static final SessionFactory FACTORY = new Configuration()
            .configure()
            .buildSessionFactory();

    private HibernateFactory() {
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }
}
