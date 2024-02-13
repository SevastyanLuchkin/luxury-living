package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
