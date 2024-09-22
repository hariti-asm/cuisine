package hariti.asmaa.ma.batiCuisine;

import hariti.asmaa.ma.batiCuisine.controllers.helpers.ClientMenu;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.ProjectMenu;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.LaborMenu;
import hariti.asmaa.ma.batiCuisine.controllers.helpers.MaterielMenu;
import hariti.asmaa.ma.batiCuisine.impl.*;
import hariti.asmaa.ma.batiCuisine.repositories.ComponentRepository;
import hariti.asmaa.ma.batiCuisine.repositories.LaborRepository;
import hariti.asmaa.ma.batiCuisine.repositories.MaterielRepository;
import hariti.asmaa.ma.batiCuisine.services.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static ClientMenu clientMenu;
    private static ProjectMenu projectMenu;

    static {
        try {
            // Initialize repositories
            ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
            ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
            LaborRepository laborRepository = new LaborRepositoryImpl();
            MaterielRepository materielRepository = new MaterielRepositoryImpl();
            ComponentRepository componentRepository = new ComponentRepositoryImpl();

            // Initialize services
            ClientService clientService = new ClientService(clientRepository);
            ComponentService componentService = new ComponentService(componentRepository);
            LaborService laborService = new LaborService(laborRepository);
            MaterielService materielService = new MaterielService(materielRepository);
            ProjectService projectService = new ProjectService(projectRepository, laborService, materielService);

            // Initialize menus
            LaborMenu laborMenu = new LaborMenu(laborService);
            MaterielMenu materielMenu = new MaterielMenu(materielService);

            clientMenu = new ClientMenu(clientService); // Initialize clientMenu first
            projectMenu = new ProjectMenu(projectService, laborMenu, materielMenu, clientService, componentService, clientMenu);

            // Set the projectMenu in clientMenu after both are initialized
            clientMenu.setProjectMenu(projectMenu);

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
