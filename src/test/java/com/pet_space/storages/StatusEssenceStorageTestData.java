package com.pet_space.storages;

import com.pet_space.models.essences.StatusEssence;

public interface StatusEssenceStorageTestData {
    StatusEssence STATUS_ESSENCE_ACTIVE = new StatusEssence(StatusEssence.StatusEssenceEnum.ACTIVE);
    StatusEssence STATUS_ESSENCE_DELETED = new StatusEssence(StatusEssence.StatusEssenceEnum.DELETED);
    StatusEssence STATUS_ESSENCE_INACTIVE = new StatusEssence(StatusEssence.StatusEssenceEnum.INACTIVE);
}
