package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.repositories.EstimateRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class EstimateRepositoryImpl  implements EstimateRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Estimate";

    public EstimateRepositoryImpl() throws SQLException {
    }

    @Override
    public void save(Estimate estimate) {
        String sql = "INSERT INTO " + tableName + " (issue_date, validity_date, status, project_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(estimate.getIssueDate()));
            stmt.setDate(2, Date.valueOf(estimate.getValidityDate()));
            stmt.setString(3, estimate.getStatus().name());
            stmt.setObject(4, estimate.getProject().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Estimate findById(UUID id) {
        return null;
    }

    @Override
    public List<Estimate> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
