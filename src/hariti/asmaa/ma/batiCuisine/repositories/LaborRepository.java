package hariti.asmaa.ma.batiCuisine.repositories;

import hariti.asmaa.ma.batiCuisine.entities.Labor;

import java.util.List;

public interface LaborRepository {
    Labor addLabor(Labor labor) ;
List<Labor> saveAll(List<Labor> labors);
}
