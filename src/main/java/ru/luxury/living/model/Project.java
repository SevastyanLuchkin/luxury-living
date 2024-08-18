package ru.luxury.living.model;

import io.hypersistence.utils.hibernate.type.array.LongArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

@ToString
@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Project extends BaseEntity {

    private String title;

    private String description;

    private Long imageId;

    @Type(LongArrayType.class)
    @Column(columnDefinition = "int8[]")
    private Long[] imageIds;

    private Integer number;
}
