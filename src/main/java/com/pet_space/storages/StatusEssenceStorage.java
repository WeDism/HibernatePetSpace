package com.pet_space.storages;

import com.pet_space.models.essences.StatusEssence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class StatusEssenceStorage {
    private static final Logger LOG = LoggerFactory.getLogger(StatusEssenceStorage.class);
    private static final StatusEssenceStorage INSTANCE = new StatusEssenceStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getInstance();

    private StatusEssenceStorage() {
    }

    public static StatusEssenceStorage getInstance() {
        return INSTANCE;
    }

    public void add(StatusEssence statusEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(statusEssence);
            session.getTransaction().commit();
        }
    }

    public Optional contains(StatusEssence statusEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from StatusEssence se where se.id=:name");
            query.setParameter("name", statusEssence.getStatusEssenceEnum());
            return query.uniqueResultOptional();
        }
    }

    public List getAll() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("from StatusEssence").list();
        }
    }

    public void delete(StatusEssence statusEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(statusEssence);
            session.getTransaction().commit();
        }
    }
}
