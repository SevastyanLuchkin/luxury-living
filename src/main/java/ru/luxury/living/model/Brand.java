package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Brand extends BaseEntity {

    @NotBlank
    private String title;

    private String description;

    private Long imageId;

    private Boolean active = true;

    private Long number;

    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Collection> collections;
}
