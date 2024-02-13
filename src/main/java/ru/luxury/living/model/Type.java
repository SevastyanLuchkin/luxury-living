package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Type extends BaseEntity {

    private String title;

    private String description;

    private Boolean active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<Product> products;

    private Integer number;
}
