package com.pet_space.storages;

import com.pet_space.models.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class PetStorage {
    private static final Logger LOG = LoggerFactory.getLogger(PetStorage.class);
    private static final PetStorage INSTANCE = new PetStorage();
    private final SessionFactory sessionFactory = HibernateFactory.getInstance();

    public static PetStorage getInstance() {
        return INSTANCE;
    }

    public void add(Pet pet) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(pet);
            session.getTransaction().commit();
        }
    }

    public void update(Pet pet) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(pet);
            session.getTransaction().commit();
        }
    }

    public Optional<Pet> findById(Pet pet) {
        return this.findById(pet.getPetId());
    }

    public Optional<Pet> findById(UUID id) {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.of(session.get(Pet.class, id));
        }
    }

    public void delete(Pet pet) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(pet);
            session.getTransaction().commit();
        }
    }

}
