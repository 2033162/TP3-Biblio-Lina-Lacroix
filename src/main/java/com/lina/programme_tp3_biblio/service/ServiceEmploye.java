package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Employe;
import com.lina.programme_tp3_biblio.modele.Fonction;
import com.lina.programme_tp3_biblio.repository.EmployeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceEmploye {

    private EmployeRepository employeRepository;

    public ServiceEmploye(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public Employe saveEmploye(String nom, String prenom, Fonction fonction) {
        return employeRepository.save(new Employe(nom, prenom, fonction));
    }

    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    public void removeEmploye(Employe employe) {
        employeRepository.delete(employe);
    }

    public Optional<Employe> getEmploye(long employeID) {
        return employeRepository.findById(employeID);
    }
}