package hariti.asmaa.ma.batiCuisine.repositories;

import hariti.asmaa.ma.batiCuisine.entities.Materiel;

import java.util.List;

public interface MaterielRepository {

    void addMateriel(Materiel materiel);
    void saveAll(List<Materiel> materiels);
}
