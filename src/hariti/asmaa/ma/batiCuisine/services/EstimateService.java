package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.repositories.EstimateRepository;

import java.util.Optional;
import java.util.UUID;

public class EstimateService {
    private  final EstimateRepository estimateRepository;
    public EstimateService(EstimateRepository estimateRepository) {
        this.estimateRepository = estimateRepository;
    }
    public void save(Estimate estimate) {
        estimateRepository.save(estimate);
    }
    public Optional<Estimate> findById(UUID estimateId) {
      return  estimateRepository.findById(estimateId);
    }
    public void delete(UUID estimateId) {
        estimateRepository.delete(estimateId);
    }

    }
