package com.pet_space.storages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.pet_space.storages.StatusEssenceStorageTestData.*;
import static org.junit.Assert.*;

public class StatusEssenceStorageTest extends DbInit {

    @Before
    public void setUp() {
        this.statusEssenceStorage.add(STATUS_ESSENCE_ACTIVE);
        this.statusEssenceStorage.add(STATUS_ESSENCE_DELETED);
        this.statusEssenceStorage.add(STATUS_ESSENCE_INACTIVE);
    }

    @Test
    public void getInstance() {
        assertNotNull(this.statusEssenceStorage);
    }

    @Test
    public void add() {
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_ACTIVE).isPresent());
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_DELETED).isPresent());
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_INACTIVE).isPresent());

    }

    @Test
    public void contains() {
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_ACTIVE).isPresent());
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_DELETED).isPresent());
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_INACTIVE).isPresent());
    }

    @Test
    public void getAll() {
        assertTrue(this.statusEssenceStorage.getAll().size() == 3);
    }

    @Test
    public void delete() {
        this.statusEssenceStorage.delete(STATUS_ESSENCE_DELETED);
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_ACTIVE).isPresent());
        assertFalse(this.statusEssenceStorage.contains(STATUS_ESSENCE_DELETED).isPresent());
        assertTrue(this.statusEssenceStorage.contains(STATUS_ESSENCE_INACTIVE).isPresent());
    }

    @After
    public void cleanUp() {
        this.statusEssenceStorage.delete(STATUS_ESSENCE_ACTIVE);
        this.statusEssenceStorage.delete(STATUS_ESSENCE_DELETED);
        this.statusEssenceStorage.delete(STATUS_ESSENCE_INACTIVE);
    }
}