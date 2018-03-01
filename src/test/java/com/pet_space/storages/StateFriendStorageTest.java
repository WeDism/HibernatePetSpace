package com.pet_space.storages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.pet_space.storages.StateFriendStorageTestData.*;
import static com.pet_space.storages.StatusEssenceStorageTestData.STATUS_ESSENCE_ACTIVE;
import static com.pet_space.storages.StatusEssenceStorageTestData.STATUS_ESSENCE_DELETED;
import static com.pet_space.storages.StatusEssenceStorageTestData.STATUS_ESSENCE_INACTIVE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StateFriendStorageTest extends DbInit {

    @Before
    public void setUp() {
        this.stateFriendStorage.add(STATE_FRIEND_APPROVED);
        this.stateFriendStorage.add(STATE_FRIEND_REJECTED);
        this.stateFriendStorage.add(STATE_FRIEND_REQUESTED);
    }

    @Test
    public void getInstance() {
        assertNotNull(this.stateFriendStorage);
    }

    @Test
    public void contains() {
        assertTrue(this.stateFriendStorage.contains(STATE_FRIEND_APPROVED).isPresent());
        assertTrue(this.stateFriendStorage.contains(STATE_FRIEND_REJECTED).isPresent());
        assertTrue(this.stateFriendStorage.contains(STATE_FRIEND_REQUESTED).isPresent());
    }

    @Test
    public void getAll() {
        assertTrue(this.stateFriendStorage.getAll().size() == 3);
    }

    @Test
    public void delete() {
        this.stateFriendStorage.delete(STATE_FRIEND_APPROVED);
        assertTrue(this.stateFriendStorage.contains(STATE_FRIEND_REJECTED).isPresent());
        assertFalse(this.stateFriendStorage.contains(STATE_FRIEND_APPROVED).isPresent());
        assertTrue(this.stateFriendStorage.contains(STATE_FRIEND_REQUESTED).isPresent());

    }

    @After
    public void cleanUp() {
        this.stateFriendStorage.delete(STATE_FRIEND_REJECTED);
        this.stateFriendStorage.delete(STATE_FRIEND_APPROVED);
        this.stateFriendStorage.delete(STATE_FRIEND_REQUESTED);

    }

}