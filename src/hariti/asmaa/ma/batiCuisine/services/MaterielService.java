package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Materiel;
import hariti.asmaa.ma.batiCuisine.repositories.LaborRepository;
import hariti.asmaa.ma.batiCuisine.repositories.MaterielRepository;

import java.util.List;

public class MaterielService {
    private  final MaterielRepository materielRepository;

    public MaterielService(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    public double calculateCostBeforeTVA(List<Materiel> materials) {
        return materials.stream()
                .mapToDouble(material -> (material.getUnitCost() * material.getQuantity() + material.getTransportationCost()) * material.getQualityCoefficient())
                .sum();
    }

    public double calculateTotalCostWithVAT(List<Materiel> materials, double vatRate) {
        double totalCostBeforeVAT = calculateCostBeforeTVA(materials);
        return totalCostBeforeVAT * (1 + vatRate / 100);
    }
    public  void saveAll(List<Materiel> materiels){
        materielRepository.saveAll(materiels);
    }

}
