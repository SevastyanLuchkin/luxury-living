package ru.luxury.living.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Image extends BaseEntity {

    private String title;

    private byte[] picture;
}
