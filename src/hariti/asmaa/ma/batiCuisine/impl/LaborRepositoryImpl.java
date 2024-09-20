package hariti.asmaa.ma.batiCuisine.impl;

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
        String sql = "INSERT INTO " + tableName + " (name, vat_rate, component_type, hourly_rate, work_hours, productivity_factor) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, labor.getName());
            stmt.setDouble(2, labor.getVatRate());
            stmt.setString(3, labor.getComponentType().name());
            stmt.setDouble(4, labor.getHourlyRate());
            stmt.setInt(5, labor.getWorkHours());
            stmt.setDouble(6, labor.getProductivityFactor());

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
