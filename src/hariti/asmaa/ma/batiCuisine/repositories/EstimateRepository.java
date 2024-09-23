package hariti.asmaa.ma.batiCuisine.repositories;

import hariti.asmaa.ma.batiCuisine.entities.Estimate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstimateRepository {
    void save(Estimate estimate);
   Optional <Estimate> findById(UUID id);
    List<Estimate> findAll();
    void delete(UUID id);
}
