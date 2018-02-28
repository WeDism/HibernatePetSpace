package com.pet_space.storages;

import com.pet_space.models.essences.UserEssence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class UserEssenceStorage {
    private static final Logger LOG = LoggerFactory.getLogger(UserEssenceStorage.class);
    private static final UserEssenceStorage INSTANCE = new UserEssenceStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getInstance();

    private UserEssenceStorage() {
    }

    public static UserEssenceStorage getInstance() {
        return INSTANCE;
    }

    public void add(UserEssence userEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(userEssence);
            session.getTransaction().commit();
        }
    }

    public void update(UserEssence userEssence) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(userEssence);
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public Optional<UserEssence> findByCredential(String username, String password) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "from UserEssence as ue join fetch ue.role join fetch ue.statusEssence where ue.nickname=:username and ue.password=:password"
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResultOptional();
        }
    }

    public Optional<UserEssence> findById(UserEssence userEssence) {
        return this.findById(userEssence.getUserEssenceId());
    }

    public Optional<UserEssence> findById(UUID id) {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.of(session.get(UserEssence.class, id));
        }
    }

    public void delete(UserEssence userEssence) {
        this.delete(userEssence.getUserEssenceId());
    }

    public void delete(UUID id) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(new UserEssence().setUserEssenceId(id));
            session.getTransaction().commit();
        }
    }
}
