package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;

import java.util.UUID;

public class ProjectService {

private final ProjectRepository projectRepository;

public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
}
    public Project findById(UUID projectId) {

    return projectRepository.findById(projectId);
    }
    public Project save(Project project) {
        return projectRepository.save(project);
    }
    }
