package com.pet_space.storages;

import com.pet_space.models.essences.StateFriend;

public interface StateFriendStorageTestData {
    StateFriend STATE_FRIEND_APPROVED = new StateFriend(StateFriend.StateFriendEnum.APPROVED);
    StateFriend STATE_FRIEND_REQUESTED = new StateFriend(StateFriend.StateFriendEnum.REQUESTED);
    StateFriend STATE_FRIEND_REJECTED = new StateFriend(StateFriend.StateFriendEnum.REJECTED);
}
