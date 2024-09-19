package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;

import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImpl  implements ProjectRepository {

    @Override
    public void save(Project project) {

    }

    @Override
    public Project findById(UUID id) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
