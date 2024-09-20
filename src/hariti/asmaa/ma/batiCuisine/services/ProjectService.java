package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Component;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectService {

    private final ProjectRepository projectRepository;
    private final LaborService laborService;
    private final MaterielService materielService;

    public ProjectService(ProjectRepository projectRepository, LaborService laborService, MaterielService materielService) {
        this.projectRepository = projectRepository;
        this.laborService = laborService;
        this.materielService = materielService;
    }

    public Project findById(UUID projectId) {
        return projectRepository.findById(projectId);
    }

    public Project save(Project project) {
        List<Component> components = project.getComponents();

        if (components == null) {
            components = new ArrayList<>();
            project.setComponents(components);
        }

        List<Labor> labors = components.stream()
                .filter(component -> component instanceof Labor)
                .map(component -> (Labor) component)
                .toList();

        List<Materiel> materials = components.stream()
                .filter(component -> component instanceof Materiel)
                .map(component -> (Materiel) component)
                .toList();

        Double margin = project.getMargin();

        double laborVatRate = 0;
        double materialVatRate = 0;

        double totalLaborCostBeforeTVA = laborService.calculateTotalLaborCostBeforeTVA(labors);
        double totalLaborCostWithVAT = laborService.calculateTotalLaborCostWithTVA(labors, laborVatRate);

        double totalMaterialCostBeforeTVA = materielService.calculateTotalCostBeforeTVA(materials);
        double totalMaterialCostWithVAT = materielService.calculateTotalCostWithVAT(materials, materialVatRate);

        double totalCostBeforeMargin = totalLaborCostWithVAT + totalMaterialCostWithVAT;

        double marginValue = 0;
        if (margin != null) {
            marginValue = totalCostBeforeMargin * (margin / 100);
        }

        project.setTotalCost(totalCostBeforeMargin);
        project.setMargin(marginValue);

        return projectRepository.save(project);
    }


}
