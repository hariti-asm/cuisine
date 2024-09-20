package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Component;
import hariti.asmaa.ma.batiCuisine.repositories.ComponentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ComponentRepositoryImpl  implements ComponentRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Component";

    public ComponentRepositoryImpl() throws SQLException {
    }

    @Override
    public void save(Component component) {
        if (component.getId() == null) {
            component.setId(UUID.randomUUID());
        }
        String sql = "INSERT INTO Component (id, name, quantity ,componenttype, project_id, vatrate) " +
                "VALUES (?, ?, ?, ?, ?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, component.getId());
            stmt.setString(2, component.getName());
            stmt.setInt(3, component.getQuantity());
            stmt.setString(4, component.getComponentType().name());
            stmt.setObject(5, component.getProject().getId());
            stmt.setDouble(6, component.getVatRate());

            stmt.executeUpdate();
            System.out.println("Component added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component findById(UUID id) {
        return null;
    }

    @Override
    public List<Component> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}







