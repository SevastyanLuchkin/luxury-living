package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
