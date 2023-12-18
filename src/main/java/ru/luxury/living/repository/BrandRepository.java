package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
