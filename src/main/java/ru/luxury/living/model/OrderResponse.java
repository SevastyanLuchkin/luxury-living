package ru.luxury.living.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class OrderResponse {

    private long id;

    private ProductResponse product;

    private Boolean handled;

    private String phone;

    private String email;

    private Instant created;
}
