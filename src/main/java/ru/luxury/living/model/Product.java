package ru.luxury.living.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.array.LongArrayType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Product extends BaseEntity {

    private String article;

    private Integer price;

    private Boolean inStock = true;

    private String title;

    private String description;

    private Long imageId;

    @Type(LongArrayType.class)
    @Column(columnDefinition = "int8[]")
    private Long[] imageIds;

    private Boolean active = true;

    private String country;

    private String materials;

    private String volume;

    @JsonIgnore
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> properties = new HashMap<>();

    @Type(StringArrayType.class)
    @Column(columnDefinition = "text[]")
    private String[] colors;

    @ManyToOne
    private ru.luxury.living.model.Type type;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Collection collection;
}
