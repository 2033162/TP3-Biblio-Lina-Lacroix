package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import com.lina.programme_tp3_biblio.repository.EmpruntDocumentRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceEmpruntDocuments {

    private EmpruntDocumentRepository empruntDocumentRepository;

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

    /*public String faireEmprunt(Client client, Document document) {
        return empruntDocumentRepository.faireEmprunt(client, document);
    }*/

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
}