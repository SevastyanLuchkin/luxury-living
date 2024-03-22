package ru.luxury.living.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Order extends BaseEntity {

    private Long productId;

    private String phone;

    private String email;
}
