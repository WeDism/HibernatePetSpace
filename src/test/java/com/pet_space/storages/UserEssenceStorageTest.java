package com.pet_space.storages;

import com.pet_space.models.Pet;
import com.pet_space.models.essences.Friends;
import com.pet_space.models.essences.StateFriend;
import com.pet_space.models.essences.UserEssence;
import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static com.pet_space.storages.GenusPetStorageTestData.GENUS_CAT;
import static com.pet_space.storages.GenusPetStorageTestData.GENUS_DOG;
import static com.pet_space.storages.PetStorageTestData.*;
import static com.pet_space.storages.RoleEssenceStorageTestData.ROLE_ESSENCE_ADMIN;
import static com.pet_space.storages.RoleEssenceStorageTestData.ROLE_ESSENCE_USER;
import static com.pet_space.storages.StateFriendStorageTestData.*;
import static com.pet_space.storages.StatusEssenceStorageTestData.STATUS_ESSENCE_ACTIVE;
import static com.pet_space.storages.UserEssenceStorageTestData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserEssenceStorageTest extends DbInit {

    @Before
    public void setUp() {
        this.genusPetStorage.add(GENUS_CAT);
        this.genusPetStorage.add(GENUS_DOG);

        this.roleEssenceStorage.add(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.add(ROLE_ESSENCE_USER);

        this.statusEssenceStorage.add(STATUS_ESSENCE_ACTIVE);

        this.stateFriendStorage.add(STATE_FRIEND_APPROVED);
        this.stateFriendStorage.add(STATE_FRIEND_REJECTED);
        this.stateFriendStorage.add(STATE_FRIEND_REQUESTED);

        this.userEssenceStorage.add(USER_ESSENCE_JOHN);
        this.userEssenceStorage.add(USER_ESSENCE_SIMON);
        this.userEssenceStorage.add(USER_ESSENCE_FRED);

        this.petStorage.add(PET_LAYMA);
        this.petStorage.add(PET_PERS);
        this.petStorage.add(PET_TIMON);
        this.petStorage.add(PET_TOSH);

    }

    @Test
    public void getInstance() {
        assertNotNull(this.userEssenceStorage);
    }

    @Test
    public void update() {
        final String about = "about";
        this.userEssenceStorage.update(USER_ESSENCE_JOHN.setAboutOfSelf(about));
        Optional<UserEssence> userEssenceOptionalJohn = this.userEssenceStorage.findById(USER_ESSENCE_JOHN);
        assertTrue(userEssenceOptionalJohn.isPresent());
        assertTrue(userEssenceOptionalJohn.get().getAboutOfSelf().equals(about));
    }

    @Test
    public void changeOwnerPets() {
        Optional<UserEssence> userEssenceOptionalJohn = this.userEssenceStorage.findById(USER_ESSENCE_JOHN);
        assertTrue(userEssenceOptionalJohn.isPresent());
        assertThat(userEssenceOptionalJohn.get().getPets().size(), is((int) SET_PETS.stream().filter(p -> p.getOwner().equals(USER_ESSENCE_JOHN)).count()));

        this.petStorage.update(PET_TIMON.setOwner(USER_ESSENCE_FRED));

        final Optional<UserEssence> userEssenceOptionalFred = this.userEssenceStorage.findById(USER_ESSENCE_FRED);
        assertTrue(userEssenceOptionalFred.isPresent());
        assertThat(userEssenceOptionalFred.get().getPets().size(), is((int) SET_PETS.stream().filter(p -> p.getOwner().equals(USER_ESSENCE_FRED)).count()));

        userEssenceOptionalJohn = this.userEssenceStorage.findById(USER_ESSENCE_JOHN);
        assertTrue(userEssenceOptionalJohn.isPresent());
        assertThat(userEssenceOptionalJohn.get().getPets().size(), is((int) SET_PETS.stream().filter(p -> p.getOwner().equals(USER_ESSENCE_JOHN)).count()));
    }

    @Test
    public void findByCredential() {
        assertTrue(this.userEssenceStorage.findByCredential(USER_ESSENCE_JOHN.getNickname(), USER_ESSENCE_JOHN.getPassword()).isPresent());
        assertThat(this.userEssenceStorage.findByCredential(USER_ESSENCE_JOHN.getNickname(), USER_ESSENCE_JOHN.getPassword()).orElse(null),
                is(USER_ESSENCE_JOHN));
    }

    @Test
    public void followByPets() {
        this.userEssenceStorage.update(USER_ESSENCE_FRED.setFollowByPets(new HashSet<>(Arrays.asList(PET_PERS, PET_TIMON))));

        Optional<Pet> byId = this.petStorage.findById(PET_PERS);
        assertTrue(byId.isPresent());
        assertThat(byId.get().getFollowersPet().size(), is(1));

        this.userEssenceStorage.update(USER_ESSENCE_FRED.setFollowByPets(new HashSet<>()));

        byId = this.petStorage.findById(PET_PERS);
        assertTrue(byId.isPresent());
        assertThat(byId.get().getFollowersPet().size(), is(0));
    }

    @Test
    public void friendRequest() throws CloneNotSupportedException {
        UserEssence userEssenceJohn = USER_ESSENCE_JOHN.clone().setUserEssenceId(UUID.randomUUID()).setNickname("JOHN_CLONE");
        UserEssence userEssenceFred = USER_ESSENCE_FRED.clone().setUserEssenceId(UUID.randomUUID()).setNickname("FRED_CLONE");

        this.userEssenceStorage.add(userEssenceJohn);
        this.userEssenceStorage.add(userEssenceFred);

        Friends friends = new Friends()
                .setUserEssence(userEssenceJohn)
                .setFriend(userEssenceFred)
                .setState(new StateFriend(StateFriend.StateFriendEnum.REQUESTED));

        userEssenceJohn.getRequestedFriendsFrom().add(friends);
        userEssenceFred.getRequestedFriendsTo().add(friends);

        this.userEssenceStorage.update(userEssenceJohn);
        this.userEssenceStorage.update(userEssenceFred);

        Optional<UserEssence> userEssenceOptionalJohn = this.userEssenceStorage.findById(userEssenceJohn);
        assertTrue(userEssenceOptionalJohn.isPresent());
        assertThat(userEssenceOptionalJohn.get().getRequestedFriendsFrom().size(), is(1));

        Optional<UserEssence> userEssenceOptionalFred = this.userEssenceStorage.findById(userEssenceFred);
        assertTrue(userEssenceOptionalFred.isPresent());
        assertThat(userEssenceOptionalFred.get().getRequestedFriendsTo().size(), is(1));

        userEssenceOptionalJohn = this.userEssenceStorage.findById(userEssenceJohn);
        assertTrue(userEssenceOptionalJohn.isPresent());
        assertThat(userEssenceOptionalJohn.get().getRequestedFriendsFrom().size(), is(1));

        this.friendsStorage.delete(friends);
        this.userEssenceStorage.delete(userEssenceJohn);
        this.userEssenceStorage.delete(userEssenceFred);
    }

    @Test
    public void findById() {
        final Optional<UserEssence> userEssenceOptional = this.userEssenceStorage.findById(USER_ESSENCE_JOHN);
        assertTrue(userEssenceOptional.isPresent());
        final UserEssence userEssence = userEssenceOptional.get();
        assertThat(userEssence, is(USER_ESSENCE_JOHN));
        assertThat(userEssence.getPets().size(), is((int) SET_PETS.stream().filter(p -> p.getOwner().equals(USER_ESSENCE_JOHN)).count()));
    }

    @After
    public void cleanUp() {
        this.petStorage.delete(PET_LAYMA);
        this.petStorage.delete(PET_PERS);
        this.petStorage.delete(PET_TIMON);
        this.petStorage.delete(PET_TOSH);

        this.genusPetStorage.delete(GENUS_CAT);
        this.genusPetStorage.delete(GENUS_DOG);

        this.userEssenceStorage.delete(USER_ESSENCE_JOHN);
        this.userEssenceStorage.delete(USER_ESSENCE_SIMON);
        this.userEssenceStorage.delete(USER_ESSENCE_FRED);

        this.statusEssenceStorage.delete(STATUS_ESSENCE_ACTIVE);

        this.roleEssenceStorage.delete(ROLE_ESSENCE_ADMIN);
        this.roleEssenceStorage.delete(ROLE_ESSENCE_USER);

    }
}