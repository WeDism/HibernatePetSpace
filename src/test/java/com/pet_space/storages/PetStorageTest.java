package com.pet_space.storages;

import com.pet_space.models.Pet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.pet_space.storages.GenusPetStorageTestData.GENUS_CAT;
import static com.pet_space.storages.GenusPetStorageTestData.GENUS_DOG;
import static com.pet_space.storages.PetStorageTestData.*;
import static com.pet_space.storages.RoleEssenceStorageTestData.ROLE_ESSENCE_ADMIN;
import static com.pet_space.storages.RoleEssenceStorageTestData.ROLE_ESSENCE_USER;
import static com.pet_space.storages.StatusEssenceStorageTestData.STATUS_ESSENCE_ACTIVE;
import static com.pet_space.storages.UserEssenceStorageTestData.USER_ESSENCE_FRED;
import static com.pet_space.storages.UserEssenceStorageTestData.USER_ESSENCE_JOHN;
import static com.pet_space.storages.UserEssenceStorageTestData.USER_ESSENCE_SIMON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PetStorageTest extends DbInit {

    @Before
    public void setUp() {
        this.genusPetStorage.add(GENUS_CAT);
        this.genusPetStorage.add(GENUS_DOG);

        this.statusEssenceStorage.add(STATUS_ESSENCE_ACTIVE);

        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.add(ROLE_ESSENCE_USER);

        this.userEssenceStorage.add(USER_ESSENCE_JOHN);
        this.userEssenceStorage.add(USER_ESSENCE_FRED);

        this.petStorage.add(PET_TIMON);
        this.petStorage.add(PET_PERS);
        this.petStorage.add(PET_LAYMA);
    }

    @Test
    public void getInstance() {
        assertNotNull(this.petStorage);
    }

    @Test
    public void update() {
        final String name = "timon";
        final double weight = 4.9;
        this.petStorage.update(PET_TIMON.setName(name).setWeight(weight));
        Optional<Pet> pet = this.petStorage.findById(PET_TIMON);
        assertTrue(pet.isPresent());
        assertTrue(pet.get().getName().equals(name));
        assertThat(pet.get().getWeight(), is(weight));
    }

    @Test
    public void findById() {
        assertThat(this.petStorage.findById(PET_TIMON).get(), is(PET_TIMON));
        assertThat(this.petStorage.findById(PET_TIMON).get(), not(PET_LAYMA));
        assertThat(this.petStorage.findById(PET_PERS).get(), is(PET_PERS));
    }

    @After
    public void cleanUp() {
        this.petStorage.delete(PET_TIMON);
        this.petStorage.delete(PET_PERS);
        this.petStorage.delete(PET_LAYMA);

        this.userEssenceStorage.delete(USER_ESSENCE_JOHN);
        this.userEssenceStorage.delete(USER_ESSENCE_FRED);

        this.statusEssenceStorage.delete(STATUS_ESSENCE_ACTIVE);

        this.roleEssenceStorage.delete(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.delete(ROLE_ESSENCE_USER);

        this.genusPetStorage.delete(GENUS_CAT);
        this.genusPetStorage.delete(GENUS_DOG);
    }
}