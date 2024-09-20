package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.services.ClientService;
import hariti.asmaa.ma.batiCuisine.services.ComponentService;
import hariti.asmaa.ma.batiCuisine.services.MaterielService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MaterielMenu {

    private  final ComponentService componentService;
    private final Scanner scanner;

    public MaterielMenu( ComponentService componentService) {
        this.componentService = componentService;
        this.scanner = new Scanner(System.in);
    }

    public void addMaterials(Project project, double vatRate) {
        while (true) {
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
            componentService.addComponent(material);

            System.out.println("Material added successfully!");

            System.out.print("Do you want to add another material? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

        System.out.println("All materials have been added to the project successfully!");
    }
}
