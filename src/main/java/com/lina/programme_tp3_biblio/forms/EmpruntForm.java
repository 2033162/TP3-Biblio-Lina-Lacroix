package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import com.lina.programme_tp3_biblio.modele.EtatDocument;
import lombok.Data;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Data
public class EmpruntForm {
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String id;
    private String dateInitial;
    private String dateExpire;
    private int nbrRappel;
    private String client;
    private String document;

    public EmpruntForm(String id,
                       String dateInitial,
                       String dateExpire,
                       int nbrRappel,
                       String client,
                       String document) {
        this.id = id;
        this.dateInitial = dateInitial;
        this.dateExpire = dateExpire;
        this.nbrRappel = nbrRappel;
        this.client = client;
        this.document = document;
    }

    public EmpruntForm() {
        this(new EmpruntDocuments());
    }

    public EmpruntForm(EmpruntDocuments empruntDocuments) {
        this(Long.toString(empruntDocuments.getId()),
                empruntDocuments.getDateInitial() == null ? null : DATETIMEFORMATTER.format((TemporalAccessor) empruntDocuments.getDateInitial()),
                empruntDocuments.getDateExpire() == null ? null : DATETIMEFORMATTER.format((TemporalAccessor) empruntDocuments.getDateExpire()),
                empruntDocuments.getNbrRappel(),
                empruntDocuments.getClient() == null ? null : String.valueOf(empruntDocuments.getClient()),
                empruntDocuments.getDocument() == null ? null : String.valueOf(empruntDocuments.getDocument()));
    }

    public EmpruntDocuments toEmprunt() {
        LocalDate bDateInitial;
        LocalDate bDateExpire;
        Client bClient;
        Document bDocument;
        try {
            bDateInitial = dateInitial == null ? null : LocalDate.parse(dateInitial, DATETIMEFORMATTER);
            bDateExpire = dateExpire == null ? null : LocalDate.parse(dateExpire, DATETIMEFORMATTER);
            bClient = client == null ? null : ;
            bDocument = document == null ? null : ;
        } catch (Exception e) {
            bDateInitial = null;
            bDateExpire = null;
            bClient = null;
            bDocument = null;
        }
        final EmpruntDocuments empruntDocuments = new EmpruntDocuments(
                Date.from(bDateInitial.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(bDateExpire.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                nbrRappel,
                bClient,
                bDocument);
        long oldId;
        try {
            oldId = Long.parseLong(id);
            if (oldId > 0)
                empruntDocuments.setId(oldId);
        } catch (NumberFormatException e) {}
        return empruntDocuments;
    }
}
