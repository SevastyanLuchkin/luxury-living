package ru.luxury.living.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Brand extends BaseEntity {

    @NotBlank
    private String title;

    private String description;

    private Long imageId;

    private boolean active = true;
}
