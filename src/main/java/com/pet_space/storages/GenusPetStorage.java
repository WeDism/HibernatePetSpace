package com.pet_space.storages;

import com.pet_space.models.GenusPet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class GenusPetStorage {
    private static final Logger LOG = LoggerFactory.getLogger(GenusPetStorage.class);
    private static final GenusPetStorage INSTANCE = new GenusPetStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getFactory();

    public GenusPetStorage() {
    }

    public static GenusPetStorage getInstance() {
        return INSTANCE;
    }

    public void add(GenusPet genusPet) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(genusPet);
            session.getTransaction().commit();
        }
    }

    public Optional contains(GenusPet genusPet) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from GenusPet gp where gp.name=:name");
            query.setParameter("name", genusPet.getName());
            return query.uniqueResultOptional();
        }
    }

    public List getAll() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("from GenusPet").list();
        }
    }

    public void delete(GenusPet genusPet) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(genusPet);
            session.getTransaction().commit();
        }
    }
}
