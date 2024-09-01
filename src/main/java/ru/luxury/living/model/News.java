package ru.luxury.living.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Getter
@Setter
@Entity
public class News extends BaseEntity {

    private String titleRu;

    private String titleEng;

    private String descriptionRus;

    private String descriptionEng;

    private Long imageId;

    private Long imageIds;

    @URL
    private String videoUrl;

    private Boolean active = true;

    @DateTimeFormat
    private Instant newsDate;

    @Enumerated(EnumType.STRING)
    private LangType lang;
}
