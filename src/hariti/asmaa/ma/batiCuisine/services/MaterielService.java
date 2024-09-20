package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.repositories.MaterielRepository;

import java.util.List;

public class MaterielService {
    private final MaterielRepository materielRepository;

    public MaterielService(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    public double calculateCostBeforeTVA(Materiel material) {
        return (material.getUnitCost() * material.getQuantity() + material.getTransportationCost()) * material.getQualityCoefficient();
    }

    public double calculateCostWithVAT(Materiel material, double vatRate) {
        double costBeforeVAT = calculateCostBeforeTVA(material);
        return costBeforeVAT * (1 + vatRate / 100);
    }

    public double calculateTotalCostBeforeTVA(List<Materiel> materials) {
        return materials.stream()
                .mapToDouble(this::calculateCostBeforeTVA)
                .sum();
    }

    public double calculateTotalCostWithVAT(List<Materiel> materials, double vatRate) {
        double totalCostBeforeVAT = calculateTotalCostBeforeTVA(materials);
        return totalCostBeforeVAT * (1 + vatRate / 100);
    }

    public void saveAll(List<Materiel> materials, double vatRate) {
        materielRepository.saveAll(materials);

        for (Materiel material : materials) {
            double costBeforeTVA = calculateCostBeforeTVA(material);
            double costWithVAT = calculateCostWithVAT(material, vatRate);

            System.out.println("Material: " + material.getName() + " - Cost before TVA: " + String.format("%.2f", costBeforeTVA) + " €");
            System.out.println("Material: " + material.getName() + " - Cost with TVA (" + vatRate + "%): " + String.format("%.2f", costWithVAT) + " €");
        }

        double totalCostBeforeTVA = calculateTotalCostBeforeTVA(materials);
        double totalCostWithVAT = calculateTotalCostWithVAT(materials, vatRate);

        System.out.println("Total material cost before TVA: " + String.format("%.2f", totalCostBeforeTVA) + " €");
        System.out.println("Total material cost with TVA (" + vatRate + "%): " + String.format("%.2f", totalCostWithVAT) + " €");
    }
}
