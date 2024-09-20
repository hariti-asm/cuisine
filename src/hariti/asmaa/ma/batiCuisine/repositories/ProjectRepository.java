package hariti.asmaa.ma.batiCuisine.repositories;

import hariti.asmaa.ma.batiCuisine.entities.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository {
    Project save(Project project);
    Project update(Project project);
    Project findById(UUID id);
    List<Project> findAll();
    void delete(UUID id);
}
