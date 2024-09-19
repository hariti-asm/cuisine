package hariti.asmaa.ma.batiCuisine.repositories;

import hariti.asmaa.ma.batiCuisine.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    boolean createClient(Client client);
    Optional<Client> getClientByName(String firstName);
    Optional<Client> updateClient(Client client);
    boolean deleteClient(UUID id);
}
