package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
