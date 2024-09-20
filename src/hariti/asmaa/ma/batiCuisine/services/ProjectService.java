package hariti.asmaa.ma.batiCuisine.services;

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
    private Double currentVatRate;

    public ProjectService(ProjectRepository projectRepository, LaborService laborService, MaterielService materielService) {
        this.projectRepository = projectRepository;
        this.laborService = laborService;
        this.materielService = materielService;
        this.currentVatRate = null;
    }

    public Project findById(UUID projectId) {
        return projectRepository.findById(projectId);
    }

    public Project save(Project project) {
        if (project.getMaterials() == null) {
            project.setMaterials(new ArrayList<>());
        }

        if (project.getLabors() == null) {
            project.setLabors(new ArrayList<>());
        }

        List<Materiel> materials = project.getMaterials();
        List<Labor> labors = project.getLabors();
        Double vatRate = project.getVatRate();
        Double margin = project.getMargin();

        double totalLaborCostBeforeTVA = laborService.calculateTotalLaborCostBeforeTVA(labors);
        double totalLaborCostWithVAT = laborService.calculateTotalLaborCostWithTVA(labors, vatRate);

        double totalMaterialCostBeforeTVA = materielService.calculateTotalCostBeforeTVA(materials);
        double totalMaterialCostWithVAT = materielService.calculateTotalCostWithVAT(materials, vatRate);

        double totalCostBeforeMargin = totalLaborCostWithVAT + totalMaterialCostWithVAT;

        double marginValue = 0;
        if (margin != null) {
            marginValue = totalCostBeforeMargin * (margin / 100);
        }

        project.setTotalCost(totalCostBeforeMargin);
        project.setMargin(marginValue);

        currentVatRate = vatRate;

        return projectRepository.save(project);
    }

    public Double getCurrentVatRate() {
        return currentVatRate;
    }
}
