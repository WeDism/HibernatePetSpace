package com.pet_space.storages;

import com.pet_space.models.Pet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static com.pet_space.storages.GenusPetStorageTestData.GENUS_CAT;
import static com.pet_space.storages.GenusPetStorageTestData.GENUS_DOG;
import static com.pet_space.storages.UserEssenceStorageTestData.USER_ESSENCE_FRED;
import static com.pet_space.storages.UserEssenceStorageTestData.USER_ESSENCE_JOHN;

public interface PetStorageTestData {
    Pet PET_TIMON = Pet.builder()
            .name("Timon")
            .owner(USER_ESSENCE_JOHN)
            .genusPet(GENUS_CAT)
            .weight(5.6)
            .build();
    Pet PET_PERS = Pet.builder()
            .name("Pers")
            .owner(USER_ESSENCE_JOHN)
            .genusPet(GENUS_CAT)
            .birthday(LocalDateTime.of(LocalDate.of(2011, 10, 5), LocalTime.now()))
            .build();
    Pet PET_TOSH = Pet.builder()
            .name("Tosh")
            .owner(USER_ESSENCE_JOHN)
            .genusPet(GENUS_CAT)
            .birthday(LocalDateTime.of(LocalDate.of(2012, 2, 22), LocalTime.now()))
            .build();
    Pet PET_LAYMA = Pet.builder()
            .name("Layma")
            .owner(USER_ESSENCE_FRED)
            .genusPet(GENUS_DOG)
            .weight(15.5)
            .birthday(LocalDateTime.of(LocalDate.of(2009, 12, 7), LocalTime.now()))
            .build();

    List<Pet> SET_PETS = List.of(PET_LAYMA, PET_PERS, PET_TIMON, PET_TOSH);
}
