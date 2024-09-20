package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;

import java.util.UUID;

public class ProjectService {

    private final ProjectRepository projectRepository;
    private Double currentVatRate;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.currentVatRate = null;
    }

    public Project findById(UUID projectId) {
        return projectRepository.findById(projectId);
    }

    public Project save(Project project) {
        if (project.getVatRate() != null) { // Check for null instead of 0
            currentVatRate = project.getVatRate();
        }
        return projectRepository.save(project);
    }

    public Double getCurrentVatRate() {
        return currentVatRate;
    }
}
