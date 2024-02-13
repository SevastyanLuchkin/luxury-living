package ru.luxury.living.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FaqItem extends BaseEntity {

    @NotBlank
    private String titleRu;

    @NotBlank
    private String titleEng;

    @NotBlank
    private String descriptionRu;

    @NotBlank
    private String descriptionEng;

    @Enumerated(EnumType.STRING)
    private LangType lang;

    private Boolean active = true;
}
