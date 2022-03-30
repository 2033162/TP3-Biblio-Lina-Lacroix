package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    private Date dateInscription;
    private int nbrEmpruntEnCour;

    @OneToMany(mappedBy = "client")
    List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    List<EmpruntDocuments> empruntDocuments = new ArrayList<>();

    public Client(String nom,
                  String prenom,
                  String rue,
                  String ville,
                  String codePostal,
                  String numeroTelephone,
                  Date dateInscription,
                  int nbrEmpruntEnCour) {
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numeroTelephone = numeroTelephone;
        this.dateInscription = dateInscription;
        this.nbrEmpruntEnCour = nbrEmpruntEnCour;
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
                ", dateInscription='" + dateInscription + '\'' +
                ", nbrEmpruntEnCour=" + nbrEmpruntEnCour +
                '}';
    }
}
