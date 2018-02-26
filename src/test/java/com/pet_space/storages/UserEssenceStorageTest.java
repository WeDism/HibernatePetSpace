package com.pet_space.storages;

import org.junit.Test;

import static com.pet_space.storages.RoleEssenceStorageTestData.ROLE_ESSENCE_ADMIN;
import static com.pet_space.storages.StatusEssenceStorageTestData.STATUS_ESSENCE_ACTIVE;
import static com.pet_space.storages.UserEssenceStorageTestData.*;
import static org.junit.Assert.assertNotNull;

public class UserEssenceStorageTest extends DbInit{

    @Test
    public void getInstance() {
        assertNotNull(this.userEssenceStorage);
    }

    @Test
    public void add() {
        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.statusEssenceStorage.add(STATUS_ESSENCE_ACTIVE);
        this.userEssenceStorage.add(USER_ESSENCE_DIMON);
    }

    @Test
    public void update() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void delete() {
    }
}