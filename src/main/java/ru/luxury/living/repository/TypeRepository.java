package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
