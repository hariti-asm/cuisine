package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.repositories.ClientRepository;

import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public boolean createClient(Client client) {
        return clientRepository.createClient(client);
    }
    public Optional<Client> updateClient(Client client) {
        return clientRepository.updateClient(client);
    }
    public Optional<Client> getClientByName(String name) {
        return clientRepository.getClientByName(name);
    }
}
