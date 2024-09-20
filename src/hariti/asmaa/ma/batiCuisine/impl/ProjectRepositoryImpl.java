package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ProjectState;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Project";

    public ProjectRepositoryImpl() throws SQLException {
    }

    @Override
    public Project save(Project project) {
        String sql = "INSERT INTO " + tableName + " (project_id, nameproject, surfacearea, state) VALUES (?, ?, ?, ?::projectstate)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            UUID projectId = UUID.randomUUID(); // Generate a new ID
            stmt.setObject(1, projectId);
            stmt.setString(2, project.getName());
            stmt.setDouble(3, project.getSurfaceArea());
            stmt.setString(4, project.getProjectState().name()); // Get the enum name as string
            stmt.executeUpdate();
            project.setId(projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }


    @Override
    public Project update(Project project) {
        String sql = "UPDATE " + tableName + " SET nameproject = ?, surfacearea = ?, state = ?::projectstate WHERE project_id= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setDouble(2, project.getSurfaceArea());
            stmt.setString(3, project.getProjectState().name()); // Get the enum name as string
            stmt.setObject(4, project.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project findById(UUID projectId) {
        /*String sql = "SELECT * FROM " + tableName + " WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, projectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("nameproject");
                double surfaceArea = rs.getDouble("surfacearea");
                ProjectState projectState = ProjectState.valueOf(rs.getString("project_state"));
                return new Project(projectId, name, surfaceArea, projectState);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public List<Project> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {
    }
}
