package hariti.asmaa.ma.batiCuisine.services;

import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.repositories.EstimateRepository;

public class EstimateService {
    private  final EstimateRepository estimateRepository;
    public EstimateService(EstimateRepository estimateRepository) {
        this.estimateRepository = estimateRepository;
    }
    public void save(Estimate estimate) {
        estimateRepository.save(estimate);
    }

    }
