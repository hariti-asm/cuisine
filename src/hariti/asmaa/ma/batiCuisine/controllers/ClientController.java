package hariti.asmaa.ma.batiCuisine.controllers;
import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.services.ClientService;
import java.util.Scanner;
import java.util.UUID;
public class ClientController {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    public boolean createClient() {
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter your address: ");
        String address = scanner.nextLine();
        System.out.println("Please enter your phone number: ");
        String phone = scanner.nextLine();
        Client newClient = new Client(UUID.randomUUID(), name, address, phone);
        Client  success = clientService.createClient(newClient);
        if (success != null) {
            System.out.println("Client successfully created. Welcome, " + name + "!");
            return true;
        } else {
            System.out.println("Failed to create client. Please try again.");
            return false;
        }
    }
}
