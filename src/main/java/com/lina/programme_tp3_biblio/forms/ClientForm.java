package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.Client;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class ClientForm {
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
        this(new Client());
    }

    public ClientForm(Client client) {
        this(Long.toString(client.getId()),
                client.getNom(),
                client.getPrenom(),
                client.getRue(),
                client.getVille(),
                client.getCodePostal(),
                client.getNumeroTelephone(),
                client.getDateInscription() == null ? null : DATETIMEFORMATTER.format(client.getDateInscription()),
                client.getNbrEmpruntEnCour());
    }

    public Client toClient() {
        LocalDate bDate;
        try {
            bDate = dateInscription == null ? null : LocalDate.parse(dateInscription, DATETIMEFORMATTER);
        } catch (Exception e) {
            bDate = null;
        }
        final Client client = new Client(nom,
                prenom,
                rue,
                ville,
                codePostal,
                numeroTelephone,
                bDate,
                nbrEmpruntEnCour);
        long oldId;
        try {
            oldId = Long.parseLong(id);
            if (oldId > 0)
                client.setId(oldId);
        } catch (NumberFormatException e) {}
        return client;
    }
}