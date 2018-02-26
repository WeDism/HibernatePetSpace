package com.pet_space.storages;

public abstract class DbInit {
    protected final GenusPetStorage genusPetStorage = GenusPetStorage.getInstance();
    protected final RoleEssenceStorage roleEssenceStorage = RoleEssenceStorage.getInstance();
    protected final UserEssenceStorage userEssenceStorage = UserEssenceStorage.getInstance();
    protected final StatusEssenceStorage statusEssenceStorage = StatusEssenceStorage.getInstance();
}
