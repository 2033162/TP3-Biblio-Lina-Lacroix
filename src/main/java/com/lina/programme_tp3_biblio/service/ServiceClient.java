package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ServiceClient {

    private ClientRepository clientRepository;

    public ServiceClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

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

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}