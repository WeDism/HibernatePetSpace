package com.pet_space.storages;

import com.pet_space.models.essences.RoleEssence;
import com.pet_space.models.essences.StatusEssence;
import com.pet_space.models.essences.UserEssence;

import java.util.UUID;

public interface UserEssenceStorageTestData {
    UserEssence USER_ESSENCE_DIMON = UserEssence.builder()
            .nickname("didi")
            .name("Dimon")
            .surname("Di")
            .role(new RoleEssence(RoleEssence.RoleEssenceEnum.ADMIN))
            .statusEssence(new StatusEssence(StatusEssence.StatusEssenceEnum.ACTIVE))
            .email("di@di.com")
            .password("pass")
            .build();
}
