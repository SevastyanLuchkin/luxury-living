package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.FaqItem;

public interface FaqRepository extends JpaRepository<FaqItem, Long> {
}
