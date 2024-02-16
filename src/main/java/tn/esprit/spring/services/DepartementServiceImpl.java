package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

import java.util.List;
@Slf4j
@Service
public class DepartementServiceImpl implements DepartementService {

    private static final Logger logger = LogManager.getLogger(DepartementServiceImpl.class);

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public List<Departement> retrieveAllDepartements() {
        List<Departement> departements = null;
        try {
            logger.info("In Method retrieveAllDepartements:");
            departements = departementRepository.findAll();
            logger.info("Out of Method retrieveAllDepartements with Success: {}", departements.size());
        } catch (Exception e) {
            logger.error("Out of Method retrieveAllDepartements with Errors: ", e);
        }
        return departements;
    }

    @Override
    public Departement addDepartement(Departement departement) {
        Departement dep = null;
        try {
            logger.info("In Method addDepartement");
            dep = departementRepository.save(departement);
            logger.info("Out of Method addDepartement with Success");
        } catch (Exception e) {
            logger.error("Error in addDepartement: {}", e.getMessage());
        }
        return dep;
    }

    @Override
    public void deleteDepartement(String id) {
        try {
            logger.info("In Method deleteDepartement");
            departementRepository.deleteById(Long.parseLong(id));
            logger.info("Out of Method deleteDepartement with Success");
        } catch (Exception e) {
            logger.error("Error in deleteDepartement: {}", e.getMessage());
        }
    }

    @Override
    public Departement updateDepartement(Departement departement) {
        Departement departementUpdated = null;
        try {
            logger.info("In Method updateDepartement");
            departementUpdated = departementRepository.save(departement);
            logger.info("Out of Method updateDepartement with Success");
        } catch (Exception e) {
            logger.error("Error in updateDepartement: {}", e.getMessage());
        }
        return departementUpdated;
    }

    @Override
    public Departement retrieveDepartement(String id, String info) {
        Departement departement = null;
        try {
            logger.info("In Method retrieveDepartement");
            departement = departementRepository.findById(Long.parseLong(id)).orElse(null);
            logger.info("Out of Method retrieveDepartement with Success");
        } catch (Exception e) {
            logger.error("Error in retrieveDepartement: {}", e.getMessage());
        }
        return departement;
    }
}
