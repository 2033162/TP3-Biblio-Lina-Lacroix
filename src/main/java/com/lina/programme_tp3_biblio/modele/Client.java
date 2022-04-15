package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    private String prenom;
    private String rue;
    private String ville;
    private String codePostal;
    private String numeroTelephone;
    private LocalDate dateInscription;

    @OneToMany(mappedBy = "client")
    List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    List<EmpruntDocuments> empruntDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    List<Amende> amendes = new ArrayList<>();

    public Client(String nom,
                  String prenom,
                  String rue,
                  String ville,
                  String codePostal,
                  String numeroTelephone,
                  LocalDate dateInscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numeroTelephone = numeroTelephone;
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", dateInscription='" + dateInscription +
                '}';
    }
}