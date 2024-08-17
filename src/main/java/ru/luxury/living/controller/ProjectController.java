package ru.luxury.living.controller;

import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAll() {
        return projectService.findAll();
    }

    @PostMapping
    public Project create(@RequestBody ProjectRequest request) {
        return projectService.create(request);
    }

    @PutMapping("{projectId}")
    public Project update(@PathVariable Long projectId, @RequestBody ProjectRequest request) {
        return projectService.update(projectId, request);
    }
}
