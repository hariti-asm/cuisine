package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.config.JdbcPostgresqlConnection;
import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.enums.ComponentType;
import hariti.asmaa.ma.batiCuisine.repositories.MaterielRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MaterielRepositoryImpl implements MaterielRepository {
    private final Connection connection = JdbcPostgresqlConnection.getInstance().getConnection();
    private final String tableName = "Material";

    public MaterielRepositoryImpl() throws SQLException {
    }

    @Override
    public void addMateriel(Materiel materiel) {
        String sql = "INSERT INTO " + tableName + " (id, name, vat_rate, component_type, quantity, unitcost, transportcost, qualitycoeff) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, materiel.getId());
            stmt.setString(2, materiel.getName());
            stmt.setDouble(3, materiel.getVatRate());
            stmt.setString(4, materiel.getComponentType().name());
            stmt.setDouble(5, materiel.getQuantity());
            stmt.setDouble(6, materiel.getUnitCost());
            stmt.setDouble(7, materiel.getTransportationCost());
            stmt.setDouble(8, materiel.getQualityCoefficient());

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
