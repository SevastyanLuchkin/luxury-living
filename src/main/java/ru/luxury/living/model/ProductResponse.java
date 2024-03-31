package ru.luxury.living.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductResponse {

    private Long id;

    private String article;

    private Integer price;

    private Boolean inStock;

    private String title;

    private String description;

    private Long imageId;

    private Long[] imageIds;

    private Boolean active;

    private String country;

    private String materials;

    private String volume;
}
