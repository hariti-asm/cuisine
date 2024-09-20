package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.services.MaterielService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MaterielMenu {

    private final MaterielService materielService;
    private final Scanner scanner;

    public MaterielMenu(MaterielService materielService) {
        this.materielService = materielService;
        this.scanner = new Scanner(System.in);
    }

    public void addMaterials() {
        List<Materiel> materials = new ArrayList<>();
        while (true) {
            System.out.println("--- Add Materials ---");

            System.out.print("Enter material name: ");
            String materialName = scanner.nextLine();

            System.out.print("Enter component type (ex., LABOR, MATERIAL): ");
            String componentTypeStr = scanner.nextLine();
            ComponentType componentType = ComponentType.valueOf(componentTypeStr.toUpperCase());

            System.out.print("Enter quantity of this material: ");
            double quantity = scanner.nextDouble();

            System.out.print("Enter unit cost of this material: ");
            double unitCost = scanner.nextDouble();

            System.out.print("Enter transportation cost of this material: ");
            double transportCost = scanner.nextDouble();

            System.out.print("Enter quality coefficient of the material (1.0 = standard, > 1.0 = high quality): ");
            double qualityCoefficient = scanner.nextDouble();
            scanner.nextLine();

            Materiel material = new Materiel(materialName,  componentType, quantity, unitCost, transportCost, qualityCoefficient);
            materials.add(material);
            System.out.println("Material added successfully!");

            System.out.print("Do you want to add another material? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

        materielService.saveAll(materials);
        System.out.println("All materials have been saved successfully!");
    }
}
