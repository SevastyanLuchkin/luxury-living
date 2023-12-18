package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
