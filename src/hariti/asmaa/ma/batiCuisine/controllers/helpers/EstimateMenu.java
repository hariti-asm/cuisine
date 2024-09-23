package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.EstimateStatus;
import hariti.asmaa.ma.batiCuisine.services.EstimateService;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class EstimateMenu {

    private final EstimateService estimateService;
    private final ProjectService projectService;
    private final Scanner scanner;

    public EstimateMenu(ProjectService projectService , EstimateService estimateService) throws SQLException {
        this.projectService = projectService;
        this.estimateService = estimateService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Estimate Menu:");
            System.out.println("1. Add Estimate");
            System.out.println("2. View Estimate");
            System.out.println("3. Delete Estimate");
            System.out.println("4. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addEstimate();
                    break;
                case 2:
                    viewEstimate();
                    break;
                case 3:
                    deleteEstimate();
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    private void addEstimate() {
        try {
            System.out.print("Enter issue date (YYYY-MM-DD): ");
            LocalDate issueDate = LocalDate.parse(scanner.next());

            System.out.print("Enter validity date (YYYY-MM-DD): ");
            LocalDate validityDate = LocalDate.parse(scanner.next());

            System.out.print("Enter status (e.g., ACCEPTED, REJECTED: ");
            String status = scanner.next().toUpperCase();

            System.out.print("Enter project ID: ");
            UUID projectId = UUID.fromString(scanner.next());

            Project project = projectService.findById(projectId);
            if (project == null) {
                System.out.println("Project not found.");
                return;
            }
            UUID estimateId = UUID.randomUUID();

            Estimate estimate = new Estimate(  estimateId, project, issueDate, validityDate, EstimateStatus.valueOf(status));
            estimateService.save(estimate);

            System.out.println("Estimate added successfully.");

        } catch (DateTimeException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status or project ID.");
        } catch (Exception e) {
            System.out.println("Error adding estimate: " + e.getMessage());
        }
    }

    private void viewEstimate() {
        try {
            System.out.print("Enter estimate ID: ");
            UUID estimateId = UUID.fromString(scanner.next());

            Optional<Estimate> estimate = estimateService.findById(estimateId);

            if (estimate.isPresent()) {
                System.out.println("Estimate Details:");
                System.out.println(estimate);
            } else {
                System.out.println("Estimate not found.");
            }

        } catch (Exception e) {
            System.out.println("Error viewing estimate: " + e.getMessage());
        }
    }

    private void deleteEstimate() {
        try {
            System.out.print("Enter estimate ID to delete: ");
            UUID estimateId = UUID.fromString(scanner.next());

            estimateService.delete(estimateId);
            System.out.println("Estimate deleted successfully.");

        } catch (Exception e) {
            System.out.println("Error deleting estimate: " + e.getMessage());
        }
    }
}
