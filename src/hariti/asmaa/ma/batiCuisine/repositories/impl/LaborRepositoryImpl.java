package hariti.asmaa.ma.batiCuisine.repositories.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.repositories.LaborRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LaborRepositoryImpl implements LaborRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Labor";

    public LaborRepositoryImpl() throws SQLException {
    }

    @Override
    public Labor addLabor(Labor labor) {
        String sql = "INSERT INTO " + tableName + " (name, quantity, componenttype, vatrate, project_id, hourlyrate, workinghours, workerproductivity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, labor.getName());
            stmt.setInt(2, labor.getQuantity());
            stmt.setString(3, labor.getComponentType().name());
            stmt.setDouble(4, labor.getVatRate());
            stmt.setObject(5, labor.getProject().getId());
            stmt.setDouble(6, labor.getHourlyRate());
            stmt.setInt(7, labor.getWorkHours());
            stmt.setDouble(8, labor.getProductivityFactor());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return labor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Labor> saveAll(List<Labor> labors) {
        for (Labor labor : labors) {
            addLabor(labor);
        }
        return labors;
    }
}
