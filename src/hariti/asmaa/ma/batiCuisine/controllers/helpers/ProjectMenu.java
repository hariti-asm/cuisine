package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.*;
import hariti.asmaa.ma.batiCuisine.enums.ProjectState;
import hariti.asmaa.ma.batiCuisine.services.ClientService;
import hariti.asmaa.ma.batiCuisine.services.ComponentService;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;

import java.util.*;

public class ProjectMenu {

    private final ProjectService projectService;
    private final ClientService clientService;
    private final ComponentService componentService;
    private final Scanner scanner = new Scanner(System.in);
    private final LaborMenu laborMenu;
    private final MaterielMenu materialMenu;
    private final ClientMenu clientMenu;

    public ProjectMenu(ProjectService projectService, LaborMenu laborMenu, MaterielMenu materialMenu, ClientService clientService, ComponentService componentService, ClientMenu clientMenu) {
        this.projectService = projectService;
        this.laborMenu = laborMenu;
        this.materialMenu = materialMenu;
        this.clientService = clientService;
        this.componentService = componentService;
        this.clientMenu = clientMenu;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Project Creation ---");
            clientMenu.showMenu();
        }
    }


    private Client getOrCreateClient() {
        System.out.print("Enter client ID (or press Enter to create a new client): ");
        String clientIdStr = scanner.nextLine();

        if (!clientIdStr.isEmpty()) {
            try {
                UUID clientId = UUID.fromString(clientIdStr);
                Optional<Client> clientOptional = clientService.getClientById(clientId);
                if (clientOptional.isPresent()) {
                    return clientOptional.get();
                }
                System.out.println("Client not found. Creating a new client.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid UUID format. Creating a new client.");
            }
        }

        return createNewClient();
    }

    private Client createNewClient() {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();

        System.out.print("Enter client phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter client address: ");
        String address = scanner.nextLine();

        Client newClient = new Client(UUID.randomUUID(), clientName, address, phone);
        Optional<Client> savedClientOptional = Optional.ofNullable(clientService.createClient(newClient));

        if (savedClientOptional.isPresent()) {
            System.out.println("New client created successfully.");
            return savedClientOptional.get();
        } else {
            System.out.println("Failed to create a new client.");
            return null;
        }
    }

    private void addLaborToProject() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Enter VAT rate for labor (%): ");
            double laborVatRate = Double.parseDouble(scanner.nextLine());
            laborMenu.addLabors(project, laborVatRate);
        }
    }

    private void addMaterialsToProject() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Enter VAT rate for materials (%): ");
            double materialVatRate = Double.parseDouble(scanner.nextLine());
            materialMenu.addMaterials(project, materialVatRate);
        }
    }

    public void addProject() {
        Client client = getOrCreateClient();
        if (client == null) {
            System.out.println("Failed to get or create a client. Aborting project creation.");
            return;
        }

        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();

        System.out.print("Enter surface area of the kitchen (in m²): ");
        double surfaceArea = Double.parseDouble(scanner.nextLine());

        Project project = new Project(
                UUID.randomUUID(),
                projectName,
                surfaceArea,
                0.0,
                0.0,
                ProjectState.NEW,
                Optional.of(client),
                new ArrayList<>(),
                null
        );
        projectService.save(project);

        ArrayList<Materiel> materials = new ArrayList<>();
        ArrayList<Labor> labors = new ArrayList<>();

        System.out.print("Enter VAT rate for materials (%): ");
        double materialVatRate = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter VAT rate for labor (%): ");
        double laborVatRate = Double.parseDouble(scanner.nextLine());

      double laborCost =  laborMenu.addLabors(project, laborVatRate) ;
      double materialCost = materialMenu.addMaterials(project, materialVatRate);
      double totalCost = laborCost + materialCost;

        System.out.print("Would you like to apply a profit margin to the project? (y/n): ");
        boolean applyMargin = scanner.nextLine().equalsIgnoreCase("y");
        double profitMargin = 0.0;
        if (applyMargin) {
            System.out.print("Enter profit margin percentage (%): ");
            profitMargin = Double.parseDouble(scanner.nextLine());
            project.setTotalCost(totalCost*profitMargin/100.0 + totalCost);

        }
        project.setMargin(profitMargin);
       projectService.update(project);
        System.out.println("Project successfully added!");
        printProjectDetails(project);
    }

    private Project updateProjectCost(Project project, ArrayList<Materiel> materials, ArrayList<Labor> labors) {
        HashMap<String, Double> costDetails = projectService.calculateTotalCost(project, materials, labors);

        Double totalCostWithMargin = costDetails.get("Total Cost After Margin Profit");
        if (totalCostWithMargin == null) {
            System.out.println("Error: Unable to calculate total cost.");
            return null;
        }

        project.setTotalCost(totalCostWithMargin);
        project.getComponents().addAll(materials);
        project.getComponents().addAll(labors);

        Project updatedProject = projectService.update(project);
        if (updatedProject != null) {
            System.out.println("Project cost updated successfully.");
            return updatedProject;
        } else {
            System.out.println("Error: Failed to update the project cost.");
            return null;
        }
    }
    private Project selectProject() {
        System.out.print("Enter the project ID: ");
        String projectIdStr = scanner.nextLine();
        try {
            UUID projectId = UUID.fromString(projectIdStr);
            Project project = projectService.findById(projectId);
            if (project == null) {
                System.out.println("Project not found. Please try again.");
            }
            return project;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid project ID format. Please try again.");
            return null;
        }
    }

    private void printProjectDetails(Project project) {
        System.out.println("Project details:");
        System.out.println("ID: " + project.getId());
        System.out.println("Name: " + project.getName());
        System.out.println("Total Cost: " + project.getTotalCost());
        System.out.println("Surface Area: " + project.getSurfaceArea() + " m²");
        System.out.println("State: " + project.getProjectState());
        System.out.println("Client: " + project.getClient().get().getName());
    }
    public void calculateTotalCost() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez entrer l'ID du projet pour lequel calculer le coût: ");
        String projectId = scanner.nextLine();
        Project project = projectService.findById(UUID.fromString(projectId));
        if (project == null) {
            System.out.println("Projet non trouvé.");
            return;
        }

        System.out.print("Veuillez entrer le taux de TVA (%): ");
        double tva = Double.parseDouble(scanner.nextLine());

        calculateTotalCost(project, tva);
    }

    public void calculateTotalCost(Project project, double tva) {
        materialMenu.addMaterials(project, tva);
        laborMenu.addLabors(project, tva);
    }

    public void showExistingProjects() {
        List<Project> projects = projectService.findAll();
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
        } else {
            for (Project project : projects) {
                System.out.println("Project ID: " + project.getId());
                System.out.println("Name: " + project.getName());
                System.out.println("Surface Area: " + project.getSurfaceArea());
                System.out.println("Total Cost: " + project.getTotalCost());
                System.out.println("Margin: " + project.getMargin());
                System.out.println("Project State: " + project.getProjectState());
                System.out.println("Client: " + project.getClient().orElse(null));
                System.out.println("Estimate: " + project.getEstimate());
                System.out.println("Components: " + project.getComponents());
                System.out.println("-----------");
            }
        }
    }

}