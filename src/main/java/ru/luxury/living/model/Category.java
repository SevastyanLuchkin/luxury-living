package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Category extends BaseEntity {

    private String title;

    private Boolean active = true;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Type> types = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    @ManyToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Brand> brands = new HashSet<>();

    private Integer number;
}
