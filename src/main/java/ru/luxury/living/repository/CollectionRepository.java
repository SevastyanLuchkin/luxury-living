package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Collection;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Optional<Collection> findByTitleIgnoreCase(String collection);
}
