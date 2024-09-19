package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.entities.Component;
import hariti.asmaa.ma.batiCuisine.repositories.ComponentRepository;

import java.util.List;
import java.util.UUID;

public class ComponentRepositoryImpl  implements ComponentRepository {

    @Override
    public void save(Component component) {

    }

    @Override
    public Component findById(UUID id) {
        return null;
    }

    @Override
    public List<Component> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
