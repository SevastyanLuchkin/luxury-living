package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.luxury.living.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p order by p.number asc")
    List<Project> findAllSortByNumber();
}
