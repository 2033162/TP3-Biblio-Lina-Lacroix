package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Amende;
import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import com.lina.programme_tp3_biblio.repository.AmendeRepository;
import com.lina.programme_tp3_biblio.repository.ClientRepository;
import com.lina.programme_tp3_biblio.repository.EmpruntDocumentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceClient {

    private ClientRepository clientRepository;
    private EmpruntDocumentRepository empruntDocumentRepository;
    private AmendeRepository amendeRepository;
    private ServiceEmpruntDocuments serviceEmpruntDocuments;

    public ServiceClient(ClientRepository clientRepository,
                         EmpruntDocumentRepository empruntDocumentRepository,
                         AmendeRepository amendeRepository,
                         ServiceEmpruntDocuments serviceEmpruntDocuments) {
        this.clientRepository = clientRepository;
        this.empruntDocumentRepository = empruntDocumentRepository;
        this.amendeRepository = amendeRepository;
        this.serviceEmpruntDocuments = serviceEmpruntDocuments;
    }

    public Client saveClient(String nom,
                           String prenom,
                           String rue,
                           String ville,
                           String codePostal,
                           String numeroTelephone,
                           LocalDate dateInscription) {
        return clientRepository.save(new Client(nom,
                prenom,
                rue,
                ville,
                codePostal,
                numeroTelephone,
                dateInscription));
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void removeClient(Client client) {
        clientRepository.delete(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClient(long id) {
        return clientRepository.findById(id);
    }

    public String listeFrais(Client client) {
        String listeFrais = "";
        double totalAmendes = 0;
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);



        List<EmpruntDocuments> empruntDocuments = empruntDocumentRepository.getClientEmpruntRetard(client.getId());
        if (empruntDocuments.size() > 0) {
            for(int i = 0; i < empruntDocuments.size(); i++) {
                EmpruntDocuments empruntDocument = empruntDocuments.get(i);
                double amende = serviceEmpruntDocuments.calculAmende(today, empruntDocument.getDateExpire());
                totalAmendes += amende;
                listeFrais += empruntDocument.toString() + " Amende " + amende;
            }
        }

        List<Amende> amendes = amendeRepository.getClientAmendes(client.getId());
        if (amendes.size() > 0) {
            for(int i = 0; i < amendes.size(); i++) {
                Amende amende = amendes.get(i);
                totalAmendes += amende.getSommeAmende();
                listeFrais += amende.toString();
            }
        }

        return listeFrais;
    }
}