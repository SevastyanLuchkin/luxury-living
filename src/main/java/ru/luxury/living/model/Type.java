package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Type extends BaseEntity {

    private String title;

    private String description;

    private Boolean active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private Set<Product> products;

    private Integer number;
}
