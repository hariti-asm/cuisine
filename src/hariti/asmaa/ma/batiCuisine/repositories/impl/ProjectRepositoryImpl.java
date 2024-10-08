package hariti.asmaa.ma.batiCuisine.repositories.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Client;
import hariti.asmaa.ma.batiCuisine.entities.Component;
import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.entities.Project;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.enums.EstimateStatus;
import hariti.asmaa.ma.batiCuisine.enums.ProjectState;
import hariti.asmaa.ma.batiCuisine.repositories.ProjectRepository;
import hariti.asmaa.ma.batiCuisine.services.ProjectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Project";
    private  final ProjectService projectService;
    public ProjectRepositoryImpl(ProjectService projectService) throws SQLException {
        this.projectService = projectService;
    }

    @Override
    public Project save(Project project) {
        String sql = "INSERT INTO " + tableName +
                " (id, projectname, profitmargin, totalcost, projectstate, client_id, estimate_id, surfacearea) " +
                "VALUES (?, ?, ?, ?, ?::projectstate, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            UUID projectId = UUID.randomUUID();
            stmt.setObject(1, projectId);
            stmt.setString(2, project.getName());
            stmt.setObject(3, project.getMargin());
            stmt.setObject(4, project.getTotalCost());
            stmt.setString(5, project.getProjectState().name());

            if (project.getClient().isPresent()) {
                stmt.setObject(6, project.getClient().get().getId());
            } else {
                stmt.setNull(6, java.sql.Types.OTHER);
            }

            if (project.getEstimate() != null) {
                stmt.setObject(7, project.getEstimate().getId());
            } else {
                stmt.setNull(7, java.sql.Types.OTHER);
            }

            stmt.setDouble(8, project.getSurfaceArea());

            stmt.executeUpdate();
            project.setId(projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }


    @Override
    public Project update(Project project) {
        String sql = "UPDATE " + tableName + " SET projectname = ?, profitmargin = ?, totalcost = ?, surfacearea = ?, projectstate = ?::projectstate WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setObject(2, project.getMargin());
            stmt.setObject(3, project.getTotalCost());
            stmt.setDouble(4, project.getSurfaceArea());
            stmt.setString(5, project.getProjectState().name());
            stmt.setObject(6, project.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }


    @Override
    public Project findById(UUID projectId) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, projectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("projectname");
                double surfaceArea = rs.getDouble("surfacearea");
                Double totalCost = rs.getDouble("totalcost");
                Double margin = rs.getObject("profitmargin", Double.class);
                ProjectState projectState = ProjectState.valueOf(rs.getString("projectstate"));
                Client client = null;
                List<Component> components = null;
                Estimate estimate = null;
                return new Project(
                        projectId,
                        name,
                        surfaceArea,
                        totalCost,
                        margin,
                        projectState,
                        Optional.empty(),
                         components,
                        estimate
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT " +
                "    p.id, " +
                "    p.projectname, " +
                "    p.profitmargin, " +
                "    p.totalcost, " +
                "    p.projectstate, " +
                "    p.client_id, " +
                "    p.estimate_id, " +
                "    p.surfacearea, " +
                "    c.name AS client_name, " +
                "    c.phone AS client_phone, " +
                "    c.address AS client_address, " +
                "    e.id AS estimate_id, " +
                "    e.issue_date, " +
                "    e.validitydate, " +
                "    e.status AS estimate_status " +
                "FROM " + tableName + " p, client c, estimate e " +
                "WHERE p.client_id = c.id " +
                "AND p.estimate_id = e.id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UUID projectId = UUID.fromString(rs.getString("id"));
                String projectName = rs.getString("projectname");
                double surfaceArea = rs.getDouble("surfacearea");
                Double totalCost = rs.getDouble("totalcost");
                Double margin = rs.getObject("profitmargin", Double.class);
                ProjectState projectState = ProjectState.valueOf(rs.getString("projectstate"));
                UUID clientId = (UUID) rs.getObject("client_id");

                // Client details
                String clientName = rs.getString("client_name");
                String clientPhone = rs.getString("client_phone");
                String clientAddress = rs.getString("client_address");
                Client client = clientId != null
                        ? new Client(clientId, clientName, clientAddress, clientPhone)
                        : null;

                // Estimate details
                UUID estimateId = UUID.fromString(rs.getString("estimate_id"));
                LocalDate issueDate = rs.getDate("issue_date").toLocalDate();
                LocalDate validityDate = rs.getDate("validitydate").toLocalDate();
                EstimateStatus estimateStatus = EstimateStatus.valueOf(rs.getString("estimate_status"));

                Project tempProject = new Project();

                Estimate estimate = new Estimate(estimateId, tempProject, issueDate, validityDate, estimateStatus);

                List<Component> components = getComponentsForProject(projectId);

                Project project = new Project(projectId, projectName, surfaceArea, totalCost, margin, projectState,
                        Optional.ofNullable(client), components, estimate);
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving projects", e);
        }

        return projects;
    }

    private List<Component> getComponentsForProject(UUID projectId) {
        List<Component> components = new ArrayList<>();
        String sql = "SELECT * FROM component WHERE project_id = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setObject(1, projectId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Fetching component for project ID: " + projectId);

                    components.add(extractComponentFromResultSet(rs, projectId));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving components for project: " + projectId, e);
        }

        System.out.println("Components found for project ID " + projectId + ": " + components.size());

        return components;
    }

    private Component extractComponentFromResultSet(ResultSet rs, UUID projectId) throws SQLException {
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        ComponentType componentType = ComponentType.valueOf(rs.getString("componenttype"));
        double vatRate = rs.getDouble("vatrate");
        Project project = projectService.findById(projectId);
        return new Component(name, componentType, project, vatRate, quantity);
    }
    @Override
    public void delete(UUID id) {
    }


}
