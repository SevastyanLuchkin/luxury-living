package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Brand;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByTitle(String baccarat);
}
