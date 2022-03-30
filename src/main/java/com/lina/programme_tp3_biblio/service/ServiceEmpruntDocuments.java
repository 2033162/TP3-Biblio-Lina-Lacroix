package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import com.lina.programme_tp3_biblio.repository.EmpruntDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ServiceEmpruntDocuments {

    @Autowired
    private EmpruntDocumentRepository empruntDocumentRepository;

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

    public EmpruntDocuments getEmpruntDocuments(long empruntDocumentsId) {
        return empruntDocumentRepository.getById(empruntDocumentsId);
    }

    /*public List<EmpruntDocuments> getClientEmprunt(long clientId) {
        return empruntDocumentRepository.getClientEmprunt(clientId);
    }*/

    /*public String faireEmprunt(Client client, Document document) {
        return empruntDocumentRepository.faireEmprunt(client, document);
    }*/

    /*public Long[] getNbrEmpruntParMois() {

        List<Object[]> empruntParMois = empruntDocumentRepository.getNbrEmpruntParMois();

        Long[] nbrEmpruntParMois = new Long[] {0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
        for(int i = 0; i < empruntParMois.size(); i++) {
            Object[] empruntMois = empruntParMois.get(i);
            nbrEmpruntParMois[(int)empruntMois[0] - 1] = (Long)empruntMois[1];
        }

        return nbrEmpruntParMois;
    }*/
}
