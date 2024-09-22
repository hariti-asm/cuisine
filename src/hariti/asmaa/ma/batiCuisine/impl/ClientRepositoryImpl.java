package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.repositories.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Client";

    public ClientRepositoryImpl() throws SQLException {
    }

    @Override
    public Client createClient(Client client) {
        String query = "INSERT INTO " + tableName + " (client_id, name, address, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setObject(1, client.getId());
            pst.setString(2, client.getName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getPhone());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Client isn't created");

            }
            Client newClient2  = new Client(client.getId(), client.getName(), client.getAddress(), client.getPhone());
            System.out.println("Client created successfully");
            return newClient2 ;
        } catch (SQLException e) {
            System.err.println("Error creating client: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Client> getClientByName(String name) {
        String query = "SELECT * FROM " + tableName + " WHERE name = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    UUID id = (UUID) rs.getObject("id");
                    String Name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");

                    return Optional.of(new Client(
                            id,
                            Name,
                            address,
                            phone
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error querying client by name", e);
        }
        return Optional.empty();
    }
@Override
public Optional<Client> getClientById(UUID clientId) {
    String query = "SELECT * FROM " + tableName + " WHERE id = ?";
    try (PreparedStatement pst = connection.prepareStatement(query)) {
        pst.setObject(1, clientId);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                return Optional.of(new Client(
                        clientId,
                        name,
                        address,
                        phone
                ));
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error querying client by ID", e);
    }
    return Optional.empty();
}

    @Override
    public Optional<Client> updateClient(Client client) {
        String query = "UPDATE " + tableName + " SET name = ?, address = ?, phone = ? WHERE client_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, client.getName());
            pst.setString(2, client.getAddress());
            pst.setString(3, client.getPhone());
            pst.setObject(4, client.getId());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Client isn't updated");
                return Optional.empty();
            }
            System.out.println("Client updated successfully");
            return Optional.of(client);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating client", e);
        }
    }

    @Override
    public boolean deleteClient(UUID id) {
        String query = "DELETE FROM " + tableName + " WHERE client_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setObject(1, id);

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Client not found or already deleted");
                return false;
            }
            System.out.println("Client deleted successfully");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting client", e);
        }
    }
}
