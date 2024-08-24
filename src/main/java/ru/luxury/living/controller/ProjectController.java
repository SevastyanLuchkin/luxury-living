package ru.luxury.living.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.Project;
import ru.luxury.living.model.ProjectRequest;
import ru.luxury.living.service.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Page<Project> getAll(
            @ParameterObject @PageableDefault(sort = {"number"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return projectService.findAll(pageable);
    }

    @PostMapping
    public Project create(@RequestBody ProjectRequest request) {
        return projectService.create(request);
    }

    @PutMapping("{projectId}")
    public Project update(@PathVariable Long projectId, @RequestBody ProjectRequest request) {
        return projectService.update(projectId, request);
    }

    @DeleteMapping("{projectId}")
    public void delete(@PathVariable Long projectId) {
        projectService.delete(projectId);
    }
}
