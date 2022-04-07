package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.Employe;
import com.lina.programme_tp3_biblio.modele.Fonction;
import lombok.Data;

@Data
public class EmployeForm {
    private String id;
    private String nom;
    private String prenom;
    private Fonction fonction;

    public EmployeForm(String id, String nom, String prenom, Fonction fonction) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
    }

    public EmployeForm() {
    }

    public EmployeForm(Employe employe) {
        this(Long.toString(employe.getId()),
                employe.getNom(),
                employe.getPrenom(),
                employe.getFonction());
    }

    public Employe toEmploye() {
        return new Employe(nom, prenom, fonction);
    }
}