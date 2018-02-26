package com.pet_space.storages;

import org.junit.After;
import org.junit.Test;

import static com.pet_space.storages.RoleEssenceStorageTestData.*;
import static org.junit.Assert.*;

public class RoleEssenceStorageTest extends DbInit {

    @Test
    public void getInstance() {
        assertNotNull(this.roleEssenceStorage);
    }

    @Test
    public void add() {
        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.add(ROLE_ESSENCE_ROOT);
        this.roleEssenceStorage.add(ROLE_ESSENCE_USER);
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ADMIN).isPresent());
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ROOT).isPresent());
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_USER).isPresent());
    }

    @Test
    public void contains() {
        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.add(ROLE_ESSENCE_ROOT);

        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ADMIN).isPresent());
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ROOT).isPresent());
        assertFalse(this.roleEssenceStorage.contains(ROLE_ESSENCE_USER).isPresent());

    }

    @Test
    public void getAll() {
        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.add(ROLE_ESSENCE_ROOT);
        this.roleEssenceStorage.add(ROLE_ESSENCE_USER);
        assertTrue(this.roleEssenceStorage.getAll().size() == 3);
    }

    @Test
    public void delete() {
        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.add(ROLE_ESSENCE_ROOT);
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ADMIN).isPresent());
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ROOT).isPresent());
        assertFalse(this.roleEssenceStorage.contains(ROLE_ESSENCE_USER).isPresent());
        this.roleEssenceStorage.delete(ROLE_ESSENCE_ROOT);
        assertTrue(this.roleEssenceStorage.contains(ROLE_ESSENCE_ADMIN).isPresent());
        assertFalse(this.roleEssenceStorage.contains(ROLE_ESSENCE_ROOT).isPresent());
        assertFalse(this.roleEssenceStorage.contains(ROLE_ESSENCE_USER).isPresent());
    }

    @After
    public void cleanUp() {
        this.roleEssenceStorage.delete(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.delete(ROLE_ESSENCE_ROOT);
        this.roleEssenceStorage.delete(ROLE_ESSENCE_USER);
    }
}