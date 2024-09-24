package hariti.asmaa.ma.batiCuisine;

import hariti.asmaa.ma.batiCuisine.controllers.helpers.*;
import hariti.asmaa.ma.batiCuisine.repositories.impl.*;
import hariti.asmaa.ma.batiCuisine.repositories.*;
import hariti.asmaa.ma.batiCuisine.services.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static ClientMenu clientMenu;
    private static ProjectMenu projectMenu;
    private static EstimateMenu estimateMenu;

    static {
        try {
            // Initialize repositories
            ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
            LaborRepository laborRepository = new LaborRepositoryImpl();
            MaterielRepository materielRepository = new MaterielRepositoryImpl();
            ComponentRepository componentRepository = new ComponentRepositoryImpl();
            EstimateRepository estimateRepository = new EstimateRepositoryImpl(null);

            // Initialize services
            ClientService clientService = new ClientService(clientRepository);
            ComponentService componentService = new ComponentService(componentRepository);
            LaborService laborService = new LaborService(laborRepository);
            MaterielService materielService = new MaterielService(materielRepository);
            ProjectService projectService = new ProjectService(null, laborService, materielService);
            ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl(projectService);
            projectService.setProjectRepository(projectRepository);
            EstimateService estimateService = new EstimateService(estimateRepository);

            // Initialize menus
            LaborMenu laborMenu = new LaborMenu(laborService);
            MaterielMenu materielMenu = new MaterielMenu(materielService);
            clientMenu = new ClientMenu(clientService);
            estimateMenu = new EstimateMenu(projectService, estimateService);
            projectMenu = new ProjectMenu(projectService, laborMenu, materielMenu, clientService, componentService, clientMenu, estimateMenu);

            clientMenu.setProjectMenu(projectMenu);
            projectMenu.setEstimateMenu(estimateMenu);
        } catch (SQLException e) {
            System.err.println("Error initializing the repository: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Create a new project");
            System.out.println("2. Display existing projects");
            System.out.println("3. Calculate project cost");
            System.out.println("4. Manage clients");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    projectMenu.showMenu();
                    break;
                case "2":
                    projectMenu.showExistingProjects();
                    break;
                case "3":
                    projectMenu.calculateTotalCost();
                    break;
                case "4":
                    clientMenu.showMenu();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}