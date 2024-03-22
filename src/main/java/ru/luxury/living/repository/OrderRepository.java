package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
