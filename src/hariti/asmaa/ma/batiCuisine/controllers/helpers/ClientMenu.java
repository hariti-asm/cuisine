package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.services.ClientService;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ClientMenu {

    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public ClientMenu(ClientService clientService) {
        this.clientService = clientService;
    }

    public void showMenu() {
        System.out.println("--- Client Search ---");
        System.out.println("Do you want to search for an existing client or add a new one?");
        System.out.println("1. Search for an existing client");
        System.out.println("2. Add a new client");
        System.out.print("Choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                searchExistingClient();
                break;
            case "2":
                addNewClient();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showMenu();
                break;
        }
    }

    private void searchExistingClient() {
        System.out.println("--- Search for an Existing Client ---");
        System.out.print("Enter the client's name: ");
        String name = scanner.nextLine();

        Optional<Client> clientOptional = clientService.getClientByName(name);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.println("Client found!");
            System.out.println("Name: " + client.getName());
            System.out.println("Address: " + client.getAddress());
            System.out.println("Phone number: " + client.getPhone());

            System.out.print("Do you want to continue with this client? (y/n): ");
            String continueOption = scanner.nextLine();

            if (continueOption.equalsIgnoreCase("y")) {
                System.out.println("Continuing with client: " + client.getName());
            } else {
                System.out.println("Returning to the main menu.");
                showMenu();
            }
        } else {
            System.out.println("Client not found.");
            showMenu();
        }
    }

    private void addNewClient() {
        System.out.println("--- Add a New Client ---");
        System.out.print("Please enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Please enter the phone number: ");
        String phone = scanner.nextLine();

        Client newClient = new Client(UUID.randomUUID(), name, address, phone);
        boolean success = clientService.createClient(newClient);

        if (success) {
            System.out.println("Client successfully created.");
        } else {
            System.out.println("Failed to create client. Please try again.");
        }
    }
}
