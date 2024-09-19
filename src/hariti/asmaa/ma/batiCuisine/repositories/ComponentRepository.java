package hariti.asmaa.ma.batiCuisine.repositories;

import hariti.asmaa.ma.batiCuisine.entities.Component;

import java.util.List;
import java.util.UUID;

public interface ComponentRepository {
    void save(Component component);
    Component findById(UUID id);
    List<Component> findAll();
    void delete(UUID id);
}
