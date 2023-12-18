package ru.luxury.living.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmailSubscription extends BaseEntity {

    @Pattern(regexp = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$")
    private String email;

    private boolean active = true;
}
