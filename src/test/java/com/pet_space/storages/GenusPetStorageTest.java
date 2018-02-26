package com.pet_space.storages;

import org.junit.After;
import org.junit.Test;

import static com.pet_space.storages.GenusPetStorageTestData.GENUS_CAT;
import static com.pet_space.storages.GenusPetStorageTestData.GENUS_DOG;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GenusPetStorageTest extends DbInit{

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
        assertTrue(this.genusPetStorage.contains(GENUS_CAT).isPresent());
        assertTrue(this.genusPetStorage.contains(GENUS_CAT).isPresent());
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