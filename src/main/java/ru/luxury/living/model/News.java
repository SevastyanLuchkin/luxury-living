package ru.luxury.living.model;

import io.hypersistence.utils.hibernate.type.array.LongArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
public class News extends BaseEntity {

    private String titleRu;

    private String titleEng;

    private String descriptionRus;

    private String descriptionEng;

    private Long imageId;

    @Type(LongArrayType.class)
    @Column(columnDefinition = "int8[]")
    private Long[] imageIds;

    private String videoUrls;

    private Boolean active = true;

    @DateTimeFormat
    private Instant newsDate;

    @Enumerated(EnumType.STRING)
    private LangType lang;
}
