package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.*;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;

import java.util.ArrayList;
import java.util.HashMap;
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

        return projectRepository.save(project);
    }
    public  HashMap<String, Double> calculateTotalCost(Project project, ArrayList<Materiel> materials, ArrayList<Labor> labors) {


        HashMap<String, Double> costDetails = new HashMap<>();


        double totalMaterialCostBeforeTax = 0;
        double totalMaterialCostWithTax = 0;


        if (!materials.isEmpty()) {
            totalMaterialCostBeforeTax = materials.stream()
                    .mapToDouble(material -> material.getQuantity() * material.getUnitCost() * material.getQualityCoefficient() + material.getTransportationCost())
                    .sum();
            costDetails.put("Total Material Cost Before Tax", totalMaterialCostBeforeTax);

            double materialTaxRate = materials.getFirst().getVatRate();
            totalMaterialCostWithTax = totalMaterialCostBeforeTax * (1 + materialTaxRate / 100);
            costDetails.put("Total Material Cost With Tax", totalMaterialCostWithTax);
        } else {
            costDetails.put("Total Material Cost Before Tax", totalMaterialCostBeforeTax);
            costDetails.put("Total Material Cost With Tax", totalMaterialCostWithTax);
        }


        double totalLaborCostBeforeTax = 0;
        double totalLaborCostWithTax = 0;

        if (!labors.isEmpty()) {
            totalLaborCostBeforeTax = labors.stream()
                    .mapToDouble(labor -> labor.getHourlyRate() * labor.getWorkHours() * labor.getProductivityFactor())
                    .sum();
            costDetails.put("Total Labor Cost Before Tax", totalLaborCostBeforeTax);

            double laborTaxRate = labors.getFirst().getVatRate();
            totalLaborCostWithTax = totalLaborCostBeforeTax * (1 + laborTaxRate / 100);
            costDetails.put("Total Labor Cost With Tax", totalLaborCostWithTax);
        } else {
            costDetails.put("Total Labor Cost Before Tax", totalLaborCostBeforeTax);
            costDetails.put("Total Labor Cost With Tax", totalLaborCostWithTax);
        }


        double totalCostBeforeMargin = totalMaterialCostWithTax + totalLaborCostWithTax;
        costDetails.put("Total Cost Before Margin", totalCostBeforeMargin);

        double marginAmount = totalCostBeforeMargin * (project.getMargin() / 100);
        costDetails.put("Margin Amount", marginAmount);

        double totalCostWithMargin = totalCostBeforeMargin + marginAmount;

        costDetails.put("Total Cost After Margin Profit", totalCostWithMargin);

        return costDetails;
    }


public Project update(Project project) {
        return projectRepository.update(project);
}
public List<Project> findAll() {
        return projectRepository.findAll();
}
}
