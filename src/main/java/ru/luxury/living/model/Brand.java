package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Brand extends BaseEntity {

    @NotBlank
    private String title;

    private String description;

    private Long imageId;

    private Boolean active = true;

    private Long number;

    @JsonIgnore
    @ManyToMany
    private Set<Category> category = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Collection> collections = new HashSet<>();
}
