package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.*;
import com.lina.programme_tp3_biblio.repository.AmendeRepository;
import com.lina.programme_tp3_biblio.repository.EmpruntDocumentRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
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

    public ServiceEmpruntDocuments(EmpruntDocumentRepository empruntDocumentRepository,
                                   AmendeRepository amendeRepository) {
        this.empruntDocumentRepository = empruntDocumentRepository;
        this.amendeRepository = amendeRepository;
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

    public void deleteAllEmpruntDocuments() {
        empruntDocumentRepository.deleteAll();
    }

    public Optional<EmpruntDocuments> getEmpruntDocuments(long empruntDocumentsId) {
        return empruntDocumentRepository.findById(empruntDocumentsId);
    }

    public List<EmpruntDocuments> findAllEmpruntDocuments() {
        return empruntDocumentRepository.findAll();
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

    public double calculAmende(Calendar today, Date dateExpire) {
        long diffInMillies = Math.abs(today.getTime().getTime() - dateExpire.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return amende * diff;
    }

    public double getTotalAmendes(Client client, Calendar today) {
        double totalAmendes = 0;

        List<EmpruntDocuments> empruntDocuments = empruntDocumentRepository.getClientEmpruntRetard(client.getId());
        if (empruntDocuments.size() > 0) {
            for(int i = 0; i < empruntDocuments.size(); i++) {
                EmpruntDocuments empruntDocument = empruntDocuments.get(i);
                totalAmendes += calculAmende(today, empruntDocument.getDateExpire());
            }
        }

        List<Amende> amendes = amendeRepository.getClientAmendes(client.getId());
        if (amendes.size() > 0) {
            for(int i = 0; i < amendes.size(); i++) {
                Amende amende = amendes.get(i);
                totalAmendes += amende.getSommeAmende();
            }
        }

        return totalAmendes;
    }

    public String faireEmprunt(Client client, Document document) {
        String message = "";
        boolean peutEmprunter = true;

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        double totalFrais = getTotalAmendes(client, today);

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
                    0,
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
        Calendar today = Calendar.getInstance();
        today.setTime(dateRetour);

        EmpruntDocuments empruntDocuments = getEmpruntDocuments(client.getId(), document.getId());
        if (empruntDocuments.getDateExpire().before(dateRetour)) {
            double sommeAmende = calculAmende(today, empruntDocuments.getDateExpire());

            Amende amende = new Amende(empruntDocuments.getDateInitial(),
                    empruntDocuments.getDateExpire(), empruntDocuments.getNbrRappel(), document,
                    client, sommeAmende);
            amendeRepository.save(amende);
            double totalFrais = getTotalAmendes(client, today);

            message = "\nFrais d'amendes " + sommeAmende + "$" +
                      "\nTotal des amendes " + totalFrais + "$";
        }

        empruntDocumentRepository.delete(empruntDocuments);

        return message;
    }
}