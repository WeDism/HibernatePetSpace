package com.pet_space.storages;

import com.pet_space.models.essences.UserEssence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    public Optional<UserEssence> findById(UUID id) {
        Optional<UserEssence> result = Optional.empty();
        try (Session session = this.sessionFactory.openSession()) {
            UserEssence user = session.get(UserEssence.class, id);
            if (user != null) {
                result = Optional.of(user);
            }
        }
        return result;
    }

    public void delete(UUID id) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(new UserEssence().setUserEssenceId(id));
            session.getTransaction().commit();
        }
    }
}
