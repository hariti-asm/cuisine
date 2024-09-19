package hariti.asmaa.ma.batiCuisine;

import hariti.asmaa.ma.batiCuisine.controllers.helpers.ClientMenu;
import hariti.asmaa.ma.batiCuisine.impl.ClientRepositoryImpl;
import hariti.asmaa.ma.batiCuisine.services.ClientService;

import java.sql.SQLException;

public class Main {

    private static ClientMenu clientMenu;

    static {
        try {
            ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
            ClientService clientService = new ClientService(clientRepository);
            clientMenu = new ClientMenu(clientService);
        } catch (SQLException e) {
            System.err.println("Error initializing the ClientRepository: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        clientMenu.showMenu();


    }
}
