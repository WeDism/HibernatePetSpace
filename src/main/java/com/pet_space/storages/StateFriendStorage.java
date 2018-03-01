package com.pet_space.storages;

import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.StateFriend;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class StateFriendStorage {
    private static final Logger LOG = LoggerFactory.getLogger(StateFriendStorage.class);
    private static final StateFriendStorage INSTANCE = new StateFriendStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getInstance();

    private StateFriendStorage() {
    }

    public static StateFriendStorage getInstance() {
        return INSTANCE;
    }

    public void add(StateFriend stateFriend) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(stateFriend);
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public Optional<RoleEssence> contains(StateFriend stateFriend) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from StateFriend re where re.id=:name");
            query.setParameter("name", stateFriend.getStateFriendEnum());
            return query.uniqueResultOptional();
        }
    }

    public List getAll() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("from StateFriend").list();
        }
    }

    public void delete(StateFriend stateFriend) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(stateFriend);
            session.getTransaction().commit();
        }
    }
}
