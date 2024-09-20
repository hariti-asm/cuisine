package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ProjectState;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.LaborMenu;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.MaterielMenu;
import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.entities.Materiel;

import java.util.Scanner;
import java.util.UUID;

public class ProjectMenu {

    private final ProjectService projectService;
    private final Scanner scanner = new Scanner(System.in);
    private final LaborMenu laborMenu;
    private final MaterielMenu materialMenu;

    public ProjectMenu(ProjectService projectService, LaborMenu laborMenu, MaterielMenu materialMenu) {
        this.projectService = projectService;
        this.laborMenu = laborMenu;
        this.materialMenu = materialMenu;
    }

    public void showMenu() {
        System.out.println("--- Project Menu ---");
        System.out.println("1. Create a New Project");
        System.out.println("2. Add Labor to a Project");
        System.out.println("3. Add Materials to a Project");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                createNewProject();
                break;
            case "2":
                laborMenu.addLabors();
                break;
            case "3":
                materialMenu.addMaterials();
                break;
            case "4":
                System.out.println("Exiting...");
                return;
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
        scanner.nextLine();

        System.out.print("Enter project state: ");
        ProjectState projectState = ProjectState.valueOf(scanner.nextLine().toUpperCase());

        Double vatRate = null;
        Double margin = null;

        System.out.println("Do you want to add labor to this project? (y/n)");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            laborMenu.addLabors();
        }

        System.out.println("Do you want to add materials to this project? (y/n)");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            materialMenu.addMaterials();
        }

        System.out.println("Would you like to add a VAT rate for this project? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Please enter the VAT rate: ");
            vatRate = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.println("Would you like to set a benefit margin for this project? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Please enter the benefit margin: ");
            margin = scanner.nextDouble();
            scanner.nextLine();
        }

        Project project = new Project(UUID.randomUUID(), projectName, kitchenSurface, projectState, vatRate, margin, null);
        projectService.save(project);

        System.out.println("Project '" + projectName + "' successfully created.");
    }
}
