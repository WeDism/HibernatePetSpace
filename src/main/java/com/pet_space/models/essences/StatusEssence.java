package com.pet_space.models.essences;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status_essence")
public class StatusEssence {
    public enum StatusEssenceEnum {
        INACTIVE, DELETED, ACTIVE
    }
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEssenceEnum statusEssenceEnum;

    public StatusEssence() {
    }

    public StatusEssence(StatusEssenceEnum statusEssenceEnum) {
        super();
        this.statusEssenceEnum = statusEssenceEnum;
    }

    public StatusEssenceEnum getStatusEssenceEnum() {
        return this.statusEssenceEnum;
    }

    public void setStatusEssenceEnum(StatusEssenceEnum statusEssenceEnum) {
        this.statusEssenceEnum = statusEssenceEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusEssence)) return false;
        StatusEssence that = (StatusEssence) o;
        return getStatusEssenceEnum() == that.getStatusEssenceEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusEssenceEnum());
    }
}
