package ru.luxury.living.model;

import lombok.Data;

@Data
public class OrderRequest {

    private Long productId;

    private String phone;

    private String email;

    private Boolean handled;
}
