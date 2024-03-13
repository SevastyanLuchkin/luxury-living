package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Collection extends BaseEntity {

    private String title;

    private Boolean active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "collection")
    private Set<Product> products = new HashSet<>();

    private Integer number;
}
