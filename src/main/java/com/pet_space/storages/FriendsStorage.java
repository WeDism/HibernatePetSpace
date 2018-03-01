package com.pet_space.storages;

import com.pet_space.models.GenusPet;
import com.pet_space.models.essences.Friends;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FriendsStorage {
    private static final Logger LOG = LoggerFactory.getLogger(FriendsStorage.class);
    private static final FriendsStorage INSTANCE = new FriendsStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getInstance();

    private FriendsStorage() {
    }

    public static FriendsStorage getInstance() {
        return INSTANCE;
    }

    public void delete(Friends friends) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(friends);
            session.getTransaction().commit();
        }
    }

}
