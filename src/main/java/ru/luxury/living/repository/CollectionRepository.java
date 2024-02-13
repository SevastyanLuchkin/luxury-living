package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
