package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Component;
import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.repositories.ClientRepository;
import hariti.asmaa.ma.batiCuisine.repositories.ComponentRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class ComponentService {
    private final ComponentRepository  componentRepository;


    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }
    public  void addComponent(Component component){
        componentRepository.save(component);
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

            double materialTaxRate = materials.get(0).getVatRate();
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

            double laborTaxRate = labors.get(0).getVatRate();
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


        costDetails.put("Total Cost After Discount", totalCostWithMargin);

        return costDetails;
    }
}
