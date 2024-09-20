package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ProjectState;
import hariti.asmaa.ma.batiCuisine.services.ClientService;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.LaborMenu;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.MaterielMenu;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ProjectMenu {

    private final ProjectService projectService;
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);
    private final LaborMenu laborMenu;
    private final MaterielMenu materialMenu;

    public ProjectMenu(ProjectService projectService, LaborMenu laborMenu, MaterielMenu materialMenu, ClientService clientService) {
        this.projectService = projectService;
        this.laborMenu = laborMenu;
        this.materialMenu = materialMenu;
        this.clientService = clientService;
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
                addLaborToProject();
                break;
            case "3":
                addMaterialsToProject();
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

        System.out.print("Enter project state (e.g., PLANNING, IN_PROGRESS, COMPLETED): ");
        ProjectState projectState = ProjectState.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter client ID: ");
        UUID clientId = UUID.fromString(scanner.nextLine());

        Double margin = null;
        System.out.print("Would you like to set a benefit margin for this project? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Please enter the benefit margin: ");
            margin = scanner.nextDouble();
            scanner.nextLine();
        }

        Optional<Client> client = clientService.getClientById(clientId);
        Project project = new Project(UUID.randomUUID(), projectName, kitchenSurface, null, null, margin, projectState, Optional.ofNullable(client.orElse(null)), null, null);

        projectService.save(project);

        System.out.println("Project '" + projectName + "' successfully created.");

        System.out.print("Would you like to add labor to this project? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Please enter the VAT rate for labor: ");
            double laborVatRate = scanner.nextDouble();
            scanner.nextLine();
            laborMenu.addLabors(project, laborVatRate);
        }

        System.out.print("Would you like to add materials to this project? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Please enter the VAT rate for materials: ");
            double materialVatRate = scanner.nextDouble();
            scanner.nextLine();
            materialMenu.addMaterials(project, materialVatRate);
        }
    }

    private void addLaborToProject() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Please enter the VAT rate for labor: ");
            double vatRate = scanner.nextDouble();
            scanner.nextLine();
            laborMenu.addLabors(project, vatRate);
        }
    }

    private void addMaterialsToProject() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Please enter the VAT rate for materials: ");
            double vatRate = scanner.nextDouble();
            scanner.nextLine();
            materialMenu.addMaterials(project, vatRate);
        }
    }


    private Project selectProject() {
        System.out.print("Enter the project ID: ");
        String projectIdStr = scanner.nextLine();
        try {
            UUID projectId = UUID.fromString(projectIdStr);
            return projectService.findById(projectId);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid project ID format. Please try again.");
            return null;
        }
    }


}
