package ru.luxury.living.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "orders")
@Accessors(chain = true)
public class Order extends BaseEntity {

    private Long productId;

    private Boolean handled = false;

    private String phone;

    private String email;

    private Boolean curtains;
}
