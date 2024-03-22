package ru.luxury.living.model;

import lombok.Data;

@Data
public class OrderRequest {

    private long productId;

    private String phone;

    private String email;
}
