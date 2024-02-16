package tn.esprit.spring.services;

import tn.esprit.spring.entities.Departement;

import java.util.List;

public interface DepartementService {
    List<Departement> retrieveAllDepartements();
    Departement addDepartement(Departement departement);
    void deleteDepartement(String id);
    Departement updateDepartement(Departement departement);
    Departement retrieveDepartement(String id, String info);
}
