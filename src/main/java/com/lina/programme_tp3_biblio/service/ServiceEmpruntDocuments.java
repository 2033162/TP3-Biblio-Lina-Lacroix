package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Amende;
import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import com.lina.programme_tp3_biblio.repository.AmendeRepository;
import com.lina.programme_tp3_biblio.repository.EmpruntDocumentRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class ServiceEmpruntDocuments {

    private final double amende = 0.25;
    private EmpruntDocumentRepository empruntDocumentRepository;
    private AmendeRepository amendeRepository;

    public ServiceEmpruntDocuments(EmpruntDocumentRepository empruntDocumentRepository) {
        this.empruntDocumentRepository = empruntDocumentRepository;
    }

    public EmpruntDocuments saveEmpruntDocuments(Date dateInitial,
                                                 Date dateExpire,
                                                 int nbrRappel,
                                                 Client client,
                                                 Document document) {
        return empruntDocumentRepository.save(new EmpruntDocuments(dateInitial,
                dateExpire,
                nbrRappel,
                client,
                document));
    }

    public EmpruntDocuments saveEmpruntDocuments(EmpruntDocuments empruntDocuments) {
        return empruntDocumentRepository.save(empruntDocuments);
    }

    public void removeEmpruntDocuments(EmpruntDocuments empruntDocuments) {
        empruntDocumentRepository.delete(empruntDocuments);
    }

    public Optional<EmpruntDocuments> getEmpruntDocuments(long empruntDocumentsId) {
        return empruntDocumentRepository.findById(empruntDocumentsId);
    }

    public List<EmpruntDocuments> getClientEmprunt(long clientId) {
        return empruntDocumentRepository.getClientEmprunt(clientId);
    }

    public int getNbrEmpruntExemplaire(long empruntDocumentsId) {
        return empruntDocumentRepository.getNbrEmpruntExemplaire(empruntDocumentsId);
    }

    public EmpruntDocuments getEmpruntDocuments(long clientId, long documentId) {
        return empruntDocumentRepository.getEmpruntDocuments(clientId, documentId);
    }

    private double calculAmende(Calendar today, Date dateExpire) {
        long diffInMillies = Math.abs(today.getTime().getTime() - dateExpire.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return amende * diff;
    }

    public String faireEmprunt(Client client, Document document) {
        String message = "";
        boolean peutEmprunter = true;

        double totalFrais = 0;
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        List<EmpruntDocuments> empruntDocuments = empruntDocumentRepository.getClientEmpruntRetard(client.getId());
        if (empruntDocuments.size() > 0) {
            for(int i = 0; i < empruntDocuments.size(); i++) {
                EmpruntDocuments empruntDocument = empruntDocuments.get(i);
                totalFrais = totalFrais + calculAmende(today, empruntDocument.getDateExpire());
            }
        }

        List<Amende> amendes = amendeRepository.getClientAmendes(client.getId());
        if (amendes.size() > 0) {
            for(int i = 0; i < amendes.size(); i++) {
                Amende amende = amendes.get(i);
                totalFrais = totalFrais + amende.getSommeAmende();
            }
        }

        if (totalFrais > 0) {
            message = "\nEmprunt interdit pour cause des amendes " + totalFrais + "$ \n";
            peutEmprunter = false;
        }

        if (peutEmprunter) {
            int nbrEmprunt = getNbrEmpruntExemplaire(document.getId());
            if (nbrEmprunt >= document.getNbrExemplaire()) {
                message = "Tous les exemplaires ont été emprunté";
                peutEmprunter = false;
            }
        }

        if (peutEmprunter) {
            double tarifEmprunt;
            int periodeEmprunt;
            if (client.getVille().equalsIgnoreCase("JavaTown")) {
                //les résidents de Javatown, peuvent emprunter gratuitement
                tarifEmprunt = 0;
            } else {
                tarifEmprunt = 1;
            }
            if (document.getGenreDocument().equalsIgnoreCase(Document.C_LIVRE)) {
                periodeEmprunt = 3;
            } else if (document.getGenreDocument().equalsIgnoreCase(Document.C_CD)) {
                periodeEmprunt = 2;
            } else {
                //DVD
                periodeEmprunt = 1;
            }

            Calendar dateExpire = today;
            dateExpire.add(Calendar.WEEK_OF_YEAR, periodeEmprunt);

            var empruntDocument = new EmpruntDocuments(
                    today.getTime(),
                    dateExpire.getTime(),
                    2,
                    client,
                    document);
            saveEmpruntDocuments(empruntDocument);
            var e = getEmpruntDocuments(empruntDocument.getId());
            message = e.toString();
        }
        return message;
    }

    public BigInteger[] getNbrEmpruntParMois() {

        List<Object[]> empruntParMois = empruntDocumentRepository.getNbrEmpruntParMois();

        BigInteger[] nbrEmpruntParMois = new BigInteger[] {BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0),
                BigInteger.valueOf(0)};
        for(int i = 0; i < empruntParMois.size(); i++) {
            Object[] empruntMois = empruntParMois.get(i);
            nbrEmpruntParMois[(int)empruntMois[0] - 1] = (BigInteger)empruntMois[1];
        }

        return nbrEmpruntParMois;
    }

    public String retourDocument(Client client, Document document, Date dateRetour) {
        String message = "";
        double sommeAmende = 0;
        Calendar today = Calendar.getInstance();
        today.setTime(dateRetour);

        EmpruntDocuments empruntDocument = getEmpruntDocuments(client.getId(), document.getId());
        if (empruntDocument.getDateExpire().before(dateRetour)) {
            sommeAmende = calculAmende(today, empruntDocument.getDateExpire());

            Amende amende = new Amende(empruntDocument.getDateInitial(),
                    empruntDocument.getDateExpire(), empruntDocument.getNbrRappel(), document, client, sommeAmende);
            amendeRepository.save(amende);
        }

        empruntDocumentRepository.delete(empruntDocument);

        return message;
    }
}