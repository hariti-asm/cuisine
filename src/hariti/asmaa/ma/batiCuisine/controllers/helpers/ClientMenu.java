package hariti.asmaa.ma.batiCuisine.controllers.helpers;

import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.services.ClientService;

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
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Search for an existing client");
            System.out.println("2. Add a new client");
            System.out.println("3. Update client information");
            System.out.println("4. Delete a client");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    searchExistingClient();
                    break;
                case "2":
                    addNewClient();
                    break;
                case "3":
                    updateClient();
                    break;
                case "4":
                    deleteClient();
                    break;
                case "5":
                    keepRunning = false;
                    System.out.println("Exiting the client menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void searchExistingClient() {
        System.out.println("\n--- Search for an Existing Client ---");
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

            if (!continueOption.equalsIgnoreCase("y")) {
                System.out.println("Returning to the main menu.");
            }
        } else {
            System.out.println("Client not found.");
        }
    }

    private void addNewClient() {
        System.out.println("\n--- Add a New Client ---");
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

    private void updateClient() {
        System.out.println("\n--- Update Client Information ---");
        System.out.print("Enter the client's current name: ");
        String name = scanner.nextLine();

        Optional<Client> clientOptional = clientService.getClientByName(name);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.println("Client found!");

            System.out.print("Enter new name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            System.out.print("Enter new address (leave blank to keep current): ");
            String newAddress = scanner.nextLine();
            System.out.print("Enter new phone number (leave blank to keep current): ");
            String newPhone = scanner.nextLine();

            if (!newName.isEmpty()) {
                client.setName(newName);
            }

            if (!newAddress.isEmpty()) {
                client.setAddress(newAddress);
            }

            if (!newPhone.isEmpty()) {
                client.setPhone(newPhone);
            }

            Optional<Client> success = clientService.updateClient(client);
            if (success.isPresent()) {
                System.out.println("Client information updated successfully.");
            } else {
                System.out.println("Failed to update client information.");
            }
        } else {
            System.out.println("Client not found.");
        }
    }

    private void deleteClient() {
        System.out.println("\n--- Delete Client ---");
        System.out.print("Enter the client's name: ");
        String name = scanner.nextLine();

        Optional<Client> clientOptional = clientService.getClientByName(name);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            System.out.print("Are you sure you want to delete this client? (y/n): ");
            String confirmDelete = scanner.nextLine();

            if (confirmDelete.equalsIgnoreCase("y")) {
                boolean success = clientService.deleteClient(client.getId());
                if (success) {
                    System.out.println("Client deleted successfully.");
                } else {
                    System.out.println("Failed to delete client.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Client not found.");
        }
    }
}
