package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Category extends BaseEntity {

    private String title;

    private Boolean active = true;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Type> types;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Brand> brands;

    private Integer number;
}
