package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ServiceClient {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(String nom,
                           String prenom,
                           String rue,
                           String ville,
                           String codePostal,
                           String numeroTelephone,
                           Date dateInscription,
                           int nbrEmpruntEnCour) {
        return clientRepository.save(new Client(nom,
                prenom,
                rue,
                ville,
                codePostal,
                numeroTelephone,
                dateInscription,
                nbrEmpruntEnCour));
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void removeClient(Client client) {
        clientRepository.delete(client);
    }

    public Client getClient(long clientId) {
        return clientRepository.getById(clientId);
    }
}