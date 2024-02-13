package ru.luxury.living.model;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
public class Product extends BaseEntity {

    private String articule; //todo артикул по-челочечески

    private int price;

    private Boolean inStock;

    private String title;

    private String description;

    private long imageId;

    private String materials;

    private Boolean active = true;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> properties = new HashMap<>();

    @Type(StringArrayType.class)
    @Column(columnDefinition = "text[]")
    private String[] colors;

    @ManyToOne
    private ru.luxury.living.model.Type type;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Collection collection;
}
