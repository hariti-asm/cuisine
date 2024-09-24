package hariti.asmaa.ma.batiCuisine.repositories.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.EstimateStatus;
import hariti.asmaa.ma.batiCuisine.repositories.EstimateRepository;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstimateRepositoryImpl  implements EstimateRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Estimate";
private ProjectService projectService ;
    public EstimateRepositoryImpl(ProjectService projectService) throws SQLException {
        this.projectService = projectService;
    }

    @Override
    public void save(Estimate estimate) {
        String sql = "INSERT INTO " + tableName + " (issue_date, validitydate, status, project_id) VALUES (?, ?, ?::EstimateStatus, ?)";
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
    public Optional<Estimate> findById(UUID estimateId) {
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setObject(1, estimateId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Date issueDate = rs.getDate("issue_date");
                    Date validityDate = rs.getDate("validitydate");
                    String status = rs.getString("status");
                    UUID projectId = UUID.fromString(rs.getString("project_id"));
                    Project project = projectService.findById(projectId);

                    return Optional.of(new Estimate(
                            estimateId,
                            project,
                            issueDate.toLocalDate(),
                            validityDate.toLocalDate(),
                            EstimateStatus.valueOf(status)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error querying estimate by ID", e);
        }
        return Optional.empty();
    }


    @Override
    public List<Estimate> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
