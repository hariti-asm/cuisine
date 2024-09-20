package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.services.LaborService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LaborMenu {

    private final LaborService laborService;
    private final Scanner scanner;

    public LaborMenu(LaborService laborService) {
        this.laborService = laborService;
        this.scanner = new Scanner(System.in);
    }

    public void addLabors() {
        List<Labor> labors = new ArrayList<>();
        while (true) {
            System.out.println("--- Add Labor ---");
            System.out.print("Enter labor name: ");
            String name = scanner.nextLine();

            System.out.print("Enter hourly rate for this labor: ");
            double hourlyRate = scanner.nextDouble();

            System.out.print("Enter number of hours worked: ");
            int hoursWorked = scanner.nextInt();

            System.out.print("Enter productivity factor:(1.0 = standard, > 1.0 = high productivity) : 1.1 ");
            double productivityFactor = scanner.nextDouble();

            scanner.nextLine();

            Labor labor = new Labor(name, ComponentType.LABOR, hourlyRate, hoursWorked, productivityFactor);

            labors.add(labor);
            System.out.println("Labor added successfully!");

            System.out.print("Do you want to add another type of labor? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

        laborService.saveAll(labors);
    }
}
