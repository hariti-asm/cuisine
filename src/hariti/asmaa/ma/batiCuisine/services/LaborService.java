package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Labor;
import hariti.asmaa.ma.batiCuisine.repositories.LaborRepository;

import java.util.List;

public class LaborService {
    private final LaborRepository laborRepository;

    public LaborService(LaborRepository laborRepository) {
        this.laborRepository = laborRepository;
    }

    // Calculate the individual labor cost before TVA
    public double calculateLaborCostBeforeTVA(Labor labor) {
        return labor.getHourlyRate() * labor.getWorkHours() * labor.getProductivityFactor();
    }

    // Calculate the individual labor cost with TVA
    public double calculateLaborCostWithTVA(Labor labor, double tvaRate) {
        double costBeforeTVA = calculateLaborCostBeforeTVA(labor);
        return costBeforeTVA + (costBeforeTVA * tvaRate / 100);
    }

    public double calculateTotalLaborCostBeforeTVA(List<Labor> labors) {
        return labors.stream()
                .mapToDouble(this::calculateLaborCostBeforeTVA)
                .sum();
    }

    public double calculateTotalLaborCostWithTVA(List<Labor> labors, double tvaRate) {
        double totalCostBeforeTVA = calculateTotalLaborCostBeforeTVA(labors);
        return totalCostBeforeTVA + (totalCostBeforeTVA * tvaRate / 100);
    }

    public void saveAll(List<Labor> labors, double tvaRate) {
        laborRepository.saveAll(labors);

        for (Labor labor : labors) {
            double costBeforeTVA = calculateLaborCostBeforeTVA(labor);
            double costWithTVA = calculateLaborCostWithTVA(labor, tvaRate);

            System.out.println("Labor: " + labor.getName() + " - Cost before TVA: " + String.format("%.2f", costBeforeTVA) + " €");
            System.out.println("Labor: " + labor.getName() + " - Cost with TVA (" + tvaRate + "%): " + String.format("%.2f", costWithTVA) + " €");
        }

        double totalCostBeforeTVA = calculateTotalLaborCostBeforeTVA(labors);
        double totalCostWithTVA = calculateTotalLaborCostWithTVA(labors, tvaRate);

        System.out.println("Total labor cost before TVA: " + String.format("%.2f", totalCostBeforeTVA) + " €");
        System.out.println("Total labor cost with TVA (" + tvaRate + "%): " + String.format("%.2f", totalCostWithTVA) + " €");
    }
}
