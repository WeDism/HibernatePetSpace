package com.pet_space.storages;

import org.hibernate.cfg.Configuration;

public abstract class DbInit {
    protected final GenusPetStorage genusPetStorage = GenusPetStorage.getInstance();
    protected final RoleEssenceStorage roleEssenceStorage = RoleEssenceStorage.getInstance();
    protected final UserEssenceStorage userEssenceStorage = UserEssenceStorage.getInstance();
    protected final StatusEssenceStorage statusEssenceStorage = StatusEssenceStorage.getInstance();
    protected final PetStorage petStorage = PetStorage.getInstance();

    protected DbInit() {
        new Configuration()
                .configure()
                .buildSessionFactory().openSession().createNativeQuery("DROP ALL OBJECTS");
    }
}
