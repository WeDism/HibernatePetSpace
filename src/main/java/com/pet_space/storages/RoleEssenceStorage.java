package com.pet_space.storages;

import com.pet_space.models.essences.RoleEssence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class RoleEssenceStorage {
    private static final Logger LOG = LoggerFactory.getLogger(RoleEssenceStorage.class);
    private static final RoleEssenceStorage INSTANCE = new RoleEssenceStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getInstance();

    private RoleEssenceStorage() {
    }

    public static RoleEssenceStorage getInstance() {
        return INSTANCE;
    }

    public void add(RoleEssence roleEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(roleEssence);
            session.getTransaction().commit();
        }
    }

    public Optional contains(RoleEssence roleEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from RoleEssence re where re.id=:name");
            query.setParameter("name", roleEssence.getRoleEssenceEnum());
            return query.uniqueResultOptional();
        }
    }

    public List getAll() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("from RoleEssence").list();
        }
    }

    public void delete(RoleEssence roleEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(roleEssence);
            session.getTransaction().commit();
        }
    }
}
