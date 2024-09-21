package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.controllers.ClientController;
import hariti.asmaa.ma.batiCuisine.entities.*;
import hariti.asmaa.ma.batiCuisine.enums.ProjectState;
import hariti.asmaa.ma.batiCuisine.services.ClientService;
import hariti.asmaa.ma.batiCuisine.services.ComponentService;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.LaborMenu;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.MaterielMenu;

import java.util.*;
import java.util.stream.Collectors;

public class ProjectMenu {

    private final ProjectService projectService;
    private final ClientService clientService;
    private final ComponentService componentService;
    private final Scanner scanner = new Scanner(System.in);
    private final LaborMenu laborMenu;
    private final MaterielMenu materialMenu;

    public ProjectMenu(ProjectService projectService, LaborMenu laborMenu, MaterielMenu materialMenu, ClientService clientService, ComponentService componentService) {
        this.projectService = projectService;
        this.laborMenu = laborMenu;
        this.materialMenu = materialMenu;
        this.clientService = clientService;
        this.componentService = componentService;
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
                addProject();
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

    public void addProject() {
        System.out.print("Enter client ID: ");
        UUID clientId = UUID.fromString(scanner.nextLine());
        Optional<Client> clientOptional = clientService.getClientById(clientId);
        if (clientOptional.isEmpty()) {
            System.out.println("Client not found. Please create a new client.");
            Client newClient = clientService.createClient();
            clientOptional = Optional.of(newClient);
        }



        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();

        System.out.print("Enter surface area of the kitchen (in m²): ");
        double surfaceArea = scanner.nextDouble();
        scanner.nextLine();

        Project project = new Project(
                UUID.randomUUID(),
                projectName,
                surfaceArea,
                0.0,
                0.0,
                ProjectState.NEW,
                clientOptional,
                new ArrayList<>(),
null        );

        System.out.print("Enter VAT rate for materials: ");
        double materialVatRate = scanner.nextDouble();
        scanner.nextLine();
        materialMenu.addMaterials(project, materialVatRate);

        System.out.print("Enter VAT rate for labor: ");
        double laborVatRate = scanner.nextDouble();
        scanner.nextLine();
        laborMenu.addLabors(project, laborVatRate);

        System.out.print("Would you like to apply a profit margin to the project? (y/n): ");
        boolean applyMargin = scanner.nextLine().equalsIgnoreCase("y");
        double profitMargin = 0.0;
        if (applyMargin) {
            System.out.print("Enter profit margin percentage (%): ");
            profitMargin = scanner.nextDouble();
            scanner.nextLine();
        }

        List<Materiel> materials = new ArrayList<>();
        List<Labor> labors = new ArrayList<>();

        for (Component component : project.getComponents()) {
            if (component instanceof Materiel) {
                materials.add((Materiel) component);
            } else if (component instanceof Labor) {
                labors.add((Labor) component);
            }
        }

        HashMap<String, Double> costDetails = projectService.calculateTotalCost(project, (ArrayList<Materiel>) materials, (ArrayList<Labor>) labors);
        double totalCostBeforeMargin = costDetails.get("Total Cost After VAT");
        double finalTotalCost = totalCostBeforeMargin + (totalCostBeforeMargin * profitMargin / 100);
        project.setTotalCost(finalTotalCost);

        Project savedProject = projectService.save(project);
        project.setId(savedProject.getId());

        System.out.println("Project successfully added!");
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
