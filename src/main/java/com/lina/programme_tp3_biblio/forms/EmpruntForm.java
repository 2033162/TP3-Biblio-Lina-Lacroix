package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import lombok.Data;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@Data
public class EmpruntForm {
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String id;
    private String dateInitial;
    private String dateExpire;
    private int nbrRappel;
    private String client;
    private String titre;
    private String auteur;
    private String anneePublication;

    public EmpruntForm(String id,
                       String dateInitial,
                       String dateExpire,
                       int nbrRappel,
                       String client,
                       String titre,
                       String auteur,
                       String anneePublication) {
        this.id = id;
        this.dateInitial = dateInitial;
        this.dateExpire = dateExpire;
        this.nbrRappel = nbrRappel;
        this.client = client;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
    }

    public EmpruntForm() {
        this(new EmpruntDocuments());
    }

    public EmpruntForm(EmpruntDocuments empruntDocuments) {
        this(Long.toString(empruntDocuments.getId()),
                empruntDocuments.getDateInitial() == null ? null :
                        DATETIMEFORMATTER.format((TemporalAccessor) empruntDocuments.getDateInitial()),
                empruntDocuments.getDateExpire() == null ? null :
                        DATETIMEFORMATTER.format((TemporalAccessor) empruntDocuments.getDateExpire()),
                empruntDocuments.getNbrRappel(),
                empruntDocuments.getClient() == null ? null : empruntDocuments.getClient().getNom() + " " +
                        empruntDocuments.getClient().getPrenom(),
                empruntDocuments.getDocument() == null ? null : empruntDocuments.getDocument().getTitre(),
                empruntDocuments.getDocument() == null ? null : empruntDocuments.getDocument().getAuteur(),
                empruntDocuments.getDocument() == null ? null : String.valueOf(
                        empruntDocuments.getDocument().getAnneePublication()));
    }
}
