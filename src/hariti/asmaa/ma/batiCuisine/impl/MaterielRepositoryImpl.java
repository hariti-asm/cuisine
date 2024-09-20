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
        String sql = "INSERT INTO " + tableName + " (unitcost, quantity, transportcost, qualitycoeff, name, component_type) VALUES ( ?, ?, ?, ?, ?, ?::componenttype)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, materiel.getUnitCost());
            stmt.setDouble(2, materiel.getQuantity());
            stmt.setDouble(3, materiel.getTransportationCost());
            stmt.setDouble(4, materiel.getQualityCoefficient());
            stmt.setString(5, materiel.getName());
            stmt.setString(6, materiel.getComponentType().name());

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
