package hariti.asmaa.ma.batiCuisine.repositories.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.repositories.MaterielRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MaterielRepositoryImpl implements MaterielRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Materiel";

    public MaterielRepositoryImpl() throws SQLException {
    }

    @Override
    public void addMateriel(Materiel materiel) {
        String sql = "INSERT INTO " + tableName +
                " (name, quantity, componenttype, vatrate, project_id, transportcost, qualitycoefficient, unitcost) " +
                "VALUES (?, ?, ?::componenttype, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, materiel.getName());
            stmt.setDouble(2, materiel.getQuantity());
            stmt.setString(3, materiel.getComponentType().name());
            stmt.setDouble(4, materiel.getVatRate());
            stmt.setObject(5, materiel.getProject().getId());
            stmt.setDouble(6, materiel.getTransportationCost());
            stmt.setDouble(7, materiel.getQualityCoefficient());
            stmt.setDouble(8, materiel.getUnitCost());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<Materiel> materiels) {
        for (Materiel materiel : materiels) {
            addMateriel(materiel);
        }
    }
}
