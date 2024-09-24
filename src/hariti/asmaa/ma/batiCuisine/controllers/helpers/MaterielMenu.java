package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.services.ComponentService;
import hariti.asmaa.ma.batiCuisine.services.MaterielService;

import java.util.*;

public class MaterielMenu {
    private final MaterielService materielService;
    private final Scanner scanner;

    public MaterielMenu( MaterielService materielService) {
        this.materielService = materielService;
        this.scanner = new Scanner(System.in);
    }

    public Map<String, Double> addMaterials(Project project, double vatRate) {
        List<Materiel> materials = new ArrayList<>();
        double totalMaterialCostBeforeTVA;
        double totalMaterialCostWithTVA;

        do {
            System.out.println("--- Add Materials ---");
            System.out.print("Enter material name: ");
            String materialName = scanner.nextLine();

            System.out.print("Enter quantity of this material: ");
            Integer quantity = scanner.nextInt();

            System.out.print("Enter unit cost of this material: ");
            double unitCost = scanner.nextDouble();

            System.out.print("Enter VAT rate of this material: ");
            double tvaValue = scanner.nextDouble();

            System.out.print("Enter transportation cost of this material: ");
            double transportCost = scanner.nextDouble();

            System.out.print("Enter quality coefficient of the material (1.0 = standard, > 1.0 = high quality): ");
            double qualityCoefficient = scanner.nextDouble();
            scanner.nextLine();

            Materiel material = new Materiel(
                    materialName,
                    ComponentType.MATERIAL,
                    project,
                    tvaValue,
                    quantity,
                    unitCost,
                    transportCost,
                    qualityCoefficient
            );

            material.setVatRate(vatRate);
            materials.add(material);

            System.out.println("Material added successfully!");

            System.out.print("Do you want to add another material? (y/n): ");
        } while (scanner.nextLine().equalsIgnoreCase("y"));

        // Calculate the total costs before and with TVA
        totalMaterialCostBeforeTVA = calculateTotalMaterialCostBeforeTVA(materials);
        totalMaterialCostWithTVA = calculateTotalMaterialCostWithTVA(materials, vatRate);


        materielService.saveAll(materials, vatRate);

        Map<String, Double> result = new HashMap<>();
        result.put("TotalMaterialCostBeforeTVA", totalMaterialCostBeforeTVA);
        result.put("TotalMaterialCostWithTVA", totalMaterialCostWithTVA);

        return result;
    }

    private double calculateTotalMaterialCostBeforeTVA(List<Materiel> materials) {
        return materials.stream()
                .mapToDouble(m -> m.getQuantity() * m.getUnitCost() * m.getQualityCoefficient() + m.getTransportationCost())
                .sum();
    }

    private double calculateTotalMaterialCostWithTVA(List<Materiel> materials, double vatRate) {
        double totalCostBeforeTVA = calculateTotalMaterialCostBeforeTVA(materials);
        return totalCostBeforeTVA * (1 + vatRate / 100);
    }
}