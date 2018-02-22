package com.pet_space.storages;

import com.pet_space.models.GenusPet;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.pet_space.storages.GenusPetStorageTestData.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GenusPetStorageTest {
    final GenusPetStorage genusPetStorage = GenusPetStorage.getInstance();

    @Test
    public void getInstance() {
        assertNotNull(this.genusPetStorage);
    }

    @Test
    public void add() {
        this.genusPetStorage.add(GENUS_CAT);
    }

    @Test
    public void getAll() {
        this.genusPetStorage.add(GENUS_CAT);
        this.genusPetStorage.add(GENUS_DOG);
        assertThat(this.genusPetStorage.getAll().size(), is(2));
    }

    @Test
    public void contains() {
        this.genusPetStorage.add(GENUS_CAT);

        assertTrue(this.genusPetStorage.contains(GENUS_CAT).isPresent());
        assertFalse(this.genusPetStorage.contains(GENUS_DOG).isPresent());
    }

    @Test
    public void delete() {
        this.genusPetStorage.add(GENUS_CAT);
        this.genusPetStorage.add(GENUS_DOG);

        this.genusPetStorage.delete(GENUS_CAT);
        assertFalse(this.genusPetStorage.contains(GENUS_CAT).isPresent());
        assertTrue(this.genusPetStorage.contains(GENUS_DOG).isPresent());
    }

    @After
    public void cleanUp(){
        this.genusPetStorage.delete(GENUS_CAT);
        this.genusPetStorage.delete(GENUS_DOG);
    }
}