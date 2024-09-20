package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Component;
import hariti.asmaa.ma.batiCuisine.repositories.ClientRepository;
import hariti.asmaa.ma.batiCuisine.repositories.ComponentRepository;

public class ComponentService {
    private final ComponentRepository  componentRepository;


    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }
    public  void addComponent(Component component){
        componentRepository.save(component);
    }
}
