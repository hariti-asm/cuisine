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
            ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
            ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
            LaborRepository laborRepository = new LaborRepositoryImpl();
            MaterielRepository materielRepository = new MaterielRepositoryImpl();
            ComponentRepository componentRepository = new ComponentRepositoryImpl();
            ClientService clientService = new ClientService(clientRepository);
            ComponentService componentService = new ComponentService(componentRepository);
            LaborService laborService = new LaborService(laborRepository);
            MaterielService materielService = new MaterielService(materielRepository);
            ProjectService projectService = new ProjectService(projectRepository, laborService, materielService);

            LaborMenu laborMenu = new LaborMenu(laborService);
            MaterielMenu materielMenu = new MaterielMenu(materielService);
            projectMenu = new ProjectMenu(projectService, laborMenu, materielMenu , clientService , componentService);
            clientMenu = new ClientMenu(clientService, projectMenu);

        } catch (SQLException e) {
            System.err.println("Error initializing the repository: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Project Management System!");
            System.out.println("1. Client Menu");
            System.out.println("2. Project Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    clientMenu.showMenu();
                    break;
                case "2":
                    projectMenu.showMenu();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
