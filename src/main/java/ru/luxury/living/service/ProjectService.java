package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Project;
import ru.luxury.living.model.ProjectRequest;
import ru.luxury.living.repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project create(ProjectRequest request) {
        Project project = new Project()
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .setImageId(request.getImageId())
                .setImageIds(request.getImageIds());

        return projectRepository.save(project);
    }

    public Project update(Long projectId, ProjectRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow();

        if (request.getTitle() != null) {
            project.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            project.setDescription(request.getDescription());
        }

        if (request.getImageId() != null) {
            project.setImageId(request.getImageId());
        }

        if (request.getImageIds() != null) {
            project.setImageIds(request.getImageIds());
        }

        return projectRepository.save(project);
    }
}
