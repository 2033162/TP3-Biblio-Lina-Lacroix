package com.lina.programme_tp3_biblio;

import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lina.programme_tp3_biblio.service.*;

import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MainBiblio implements CommandLineRunner {

    @Autowired
    private ServiceClient serviceClient;
    @Autowired
    private ServiceDocument serviceDocument;
    @Autowired
    private ServiceEmploye serviceEmploye;
    @Autowired
    private ServiceEmpruntDocuments serviceEmpruntDocuments;
    @Autowired
    private ServiceReservation serviceReservation;

    public static void main(String[] args) {
        SpringApplication.run(MainBiblio.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final CD cd = serviceDocument.saveCD(
                EtatDocument.DISPONIBLE,
                "CD",
                "harry potter",
                "JK. Rolling",
                "maison edition",
                2000,
                "classique",
                "JK. Rolling",
                "michel");
        final DVD dvd = serviceDocument.saveDVD(
                EtatDocument.ENDOMMAGE,
                "DVD",
                "bobby bob",
                "lilo lee",
                "edition bop",
                2018,
                44,
                "drame");
        final Livre livre = serviceDocument.saveLivre(
                EtatDocument.EMPRUNTE,
                "livre",
                "avengers",
                "Josh whedon",
                "marvel",
                2020,
                230,
                GenreLivre.ROMAN);
        final Employe employe = serviceEmploye.saveEmploye(
                "bernadette",
                "carmier",
                Fonction.GESTIONNAIRE);
        final Client client = serviceClient.saveClient(
                "John",
                "Smith",
                "Daragon",
                "Montreal",
                "H05C42",
                "514-900-5698",
                getDateFromLocalDate(2022, 2, 20));
        final Reservation reservation = serviceReservation.saveReservation(
                new SimpleDateFormat("dd/MM/yyyy").parse("05/10/2000"),
                client,
                livre);
        final EmpruntDocuments empruntDocuments = serviceEmpruntDocuments.saveEmpruntDocuments(
                new SimpleDateFormat("dd/MM/yyyy").parse("15/04/2022"),
                new SimpleDateFormat("dd/MM/yyyy").parse("19/04/2022"),
                0,
                client,
                dvd);


        System.out.println("\nCRUD - CD");
        System.out.println(serviceDocument.getCD(cd.getId()));

        cd.setGenreMusique("jazz");
        serviceDocument.saveCD(cd);
        System.out.println(serviceDocument.getCD(cd.getId()));


        System.out.println("\nCRUD - DVD");
        System.out.println(serviceDocument.getDVD(dvd.getId()));

        dvd.setEtatDocument(EtatDocument.DISPONIBLE);
        serviceDocument.saveDVD(dvd);
        System.out.println(serviceDocument.getDVD(dvd.getId()));


        System.out.println("\nCRUD - Livre");
        System.out.println(serviceDocument.getLivre(livre.getId()));

        livre.setNbrPages(900);
        serviceDocument.saveLivre(livre);
        System.out.println(serviceDocument.getLivre(livre.getId()));


        System.out.println("\nCRUD - Employe");
        System.out.println(serviceEmploye.getEmploye(employe.getId()));

        employe.setFonction(Fonction.PREPOSE);
        serviceEmploye.saveEmploye(employe);
        System.out.println(serviceEmploye.getEmploye(employe.getId()));


        System.out.println("\nCRUD - Client");
        System.out.println(serviceClient.getClient(client.getId()));

        client.setRue("Drolet");
        serviceClient.saveClient(client);
        System.out.println(serviceClient.getClient(client.getId()));


        System.out.println("\nCRUD - Reservation");
        System.out.println(serviceReservation.getReservation(reservation.getId()));

        reservation.setDateReservation(new SimpleDateFormat("dd/MM/yyyy").parse("13/03/2022"));
        serviceReservation.saveReservation(reservation);
        System.out.println(serviceReservation.getReservation(reservation.getId()));


        System.out.println("\nCRUD - EmpruntDocuments");
        System.out.println(serviceEmpruntDocuments.getEmpruntDocuments(empruntDocuments.getId()));

        empruntDocuments.setNbrRappel(0);
        serviceEmpruntDocuments.saveEmpruntDocuments(empruntDocuments);
        System.out.println(serviceEmpruntDocuments.getEmpruntDocuments(empruntDocuments.getId()));


        System.out.println("\nRESULTAT RECHERCHE DOCUMENTS :");
        List<Document> listeDocuments = serviceDocument.searchDocument("avengers",
                "Josh whedon",
                2020,
                "livre");
        listeDocuments.forEach((document) -> {
            System.out.println(document.toStringDocument());
        });
        System.out.println();


        System.out.println("\nFaire un emprunt");
        System.out.println(serviceEmpruntDocuments.faireEmprunt(client, livre));


        System.out.println("\nListe des emprunts du client:");
        var clientEmprunt = serviceEmpruntDocuments.getClientEmprunt(client.getId());
        for (EmpruntDocuments empruntDocument: clientEmprunt) {
            System.out.println(empruntDocument);
        }
        System.out.println();


        System.out.println("\nNOMBRE D'EMPRUNT PAR MOIS :");
        BigInteger[] nbrEmpruntParMois = serviceEmpruntDocuments.getNbrEmpruntParMois();
        for (int i = 0; i < nbrEmpruntParMois.length; i++) {
            System.out.println(new DateFormatSymbols().getMonths()[i] + "  " + nbrEmpruntParMois[i]);
        }
        System.out.println();


        /*System.out.println("\nDelete cd");
        System.out.println(cd);
        serviceDocument.removeCD(cd);

        System.out.println("\nDelete dvd");
        System.out.println(dvd);
        serviceDocument.removeDVD(dvd);

        System.out.println("\nDelete empruntDocument");
        System.out.println(empruntDocuments);
        serviceEmpruntDocuments.removeEmpruntDocuments(empruntDocuments);

        System.out.println("\nDelete reservation");
        System.out.println(reservation);
        serviceReservation.removeReservation(reservation);

        System.out.println("\nDelete client");
        System.out.println(client);
        serviceClient.removeClient(client);

        System.out.println("\nDelete livre");
        System.out.println(livre);
        serviceDocument.removeLivre(livre);

        System.out.println("\nDelete employe");
        System.out.println(employe);
        serviceEmploye.removeEmploye(employe);*/

        /*System.out.println("\nDelete empruntDocument");
        serviceEmpruntDocuments.removeEmpruntDocuments(clientEmprunt.get(0));
        System.out.println(clientEmprunt.get(0));*/
    }

    private LocalDate getDateFromLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}