package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.Client;
import lombok.Data;

import java.util.Date;

@Data
public class ClientForm {
    private String id;
    private String nom;
    private String prenom;
    private String rue;
    private String ville;
    private String codePostal;
    private String numeroTelephone;
    private String dateInscription;
    private int nbrEmpruntEnCour;

    public ClientForm(String id,
                      String nom,
                  String prenom,
                  String rue,
                  String ville,
                  String codePostal,
                  String numeroTelephone,
                  String dateInscription,
                  int nbrEmpruntEnCour) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numeroTelephone = numeroTelephone;
        this.dateInscription = dateInscription;
        this.nbrEmpruntEnCour = nbrEmpruntEnCour;
    }

    public ClientForm() {
    }

    public ClientForm(Client client) {
        this(Long.toString(client.getId()),
                client.getNom(),
                client.getPrenom(),
                client.getRue(),
                client.getVille(),
                client.getCodePostal(),
                client.getNumeroTelephone(),
                null,
                client.getNbrEmpruntEnCour());
    }

    public Client toClient() {
        Date bDate = null;
        return new Client(nom,
                prenom,
                rue,
                ville,
                codePostal,
                numeroTelephone,
                bDate,
                nbrEmpruntEnCour);
    }
}