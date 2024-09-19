package hariti.asmaa.ma.batiCuisine.impl;

import hariti.asmaa.ma.batiCuisine.entities.Estimate;
import hariti.asmaa.ma.batiCuisine.repositories.EstimateRepository;

import java.util.List;
import java.util.UUID;

public class EstimateRepositoryImpl  implements EstimateRepository {
    @Override
    public void save(Estimate estimate) {

    }

    @Override
    public Estimate findById(UUID id) {
        return null;
    }

    @Override
    public List<Estimate> findAll() {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
