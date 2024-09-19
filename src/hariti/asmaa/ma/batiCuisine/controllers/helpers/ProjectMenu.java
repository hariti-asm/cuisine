package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;

import java.util.Scanner;
import java.util.UUID;

public class ProjectMenu {

    private final ProjectService projectService;
    private final Scanner scanner = new Scanner(System.in);

    public ProjectMenu(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void showMenu() {
        System.out.println("--- Project Menu ---");
        System.out.println("1. Create a New Project");
        System.out.println("Choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                createNewProject();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showMenu();
                break;
        }
    }

    private void createNewProject() {
        System.out.println("--- Creating a New Project ---");
        System.out.print("Enter the project name: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter the kitchen surface area (in m²): ");
        double kitchenSurface = scanner.nextDouble();

        boolean success = true;

        if (success) {
            System.out.println("Project '" + projectName + "' successfully created.");
        } else {
            System.out.println("Failed to create the project. Please try again.");
        }
    }
}
