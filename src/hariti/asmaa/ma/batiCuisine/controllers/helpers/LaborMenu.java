package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.services.LaborService;

import java.util.*;

public class LaborMenu {

    private final LaborService laborService;
    private final Scanner scanner;

    public LaborMenu(LaborService laborService) {
        this.laborService = laborService;
        this.scanner = new Scanner(System.in);
    }

    public Map<String, Double> addLabors(Project project, double vatRate) {
        List<Labor> labors = new ArrayList<>();
        double totalLaborCostBeforeTVA;
        double totalLaborCostWithTVA;

        do {
            System.out.println("--- Add Labor ---");
            System.out.print("Enter labor name: ");
            String name = scanner.nextLine();

            System.out.print("Enter hourly rate for this labor: ");
            double hourlyRate = scanner.nextDouble();

            System.out.print("Enter number of hours worked: ");
            int hoursWorked = scanner.nextInt();

            System.out.print("Enter productivity factor (1.0 = standard, > 1.0 = high productivity): ");
            double productivityFactor = scanner.nextDouble();

            scanner.nextLine();

            int quantity = 1;

            Labor labor = new Labor(name, ComponentType.LABOR, project, vatRate, quantity, hourlyRate, hoursWorked, productivityFactor);
            labors.add(labor);

            System.out.println("Labor added successfully!");

            System.out.print("Do you want to add another type of labor? (y/n): ");
        } while (scanner.nextLine().equalsIgnoreCase("y"));

        totalLaborCostBeforeTVA = laborService.calculateTotalLaborCostBeforeTVA(labors);
        totalLaborCostWithTVA = laborService.calculateTotalLaborCostWithTVA(labors, vatRate);

        laborService.saveAll(labors, vatRate);

        Map<String, Double> result = new HashMap<>();
        result.put("TotalLaborCostBeforeTVA", totalLaborCostBeforeTVA);
        result.put("TotalLaborCostWithTVA", totalLaborCostWithTVA);

        return result;
    }}
