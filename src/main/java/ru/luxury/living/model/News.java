package ru.luxury.living.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
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

    @DateTimeFormat
    private Instant newsDate;

    @Enumerated(EnumType.STRING)
    private LangType lang;
}
