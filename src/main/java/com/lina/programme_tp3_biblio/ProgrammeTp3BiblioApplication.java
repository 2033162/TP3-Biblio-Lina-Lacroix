package com.lina.programme_tp3_biblio;

import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lina.programme_tp3_biblio.service.*;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class ProgrammeTp3BiblioApplication implements CommandLineRunner {

    @Autowired
    private ServiceClient serviceClient;
    private ServiceDocument serviceDocument;
    private ServiceEmploye serviceEmploye;
    private ServiceEmpruntDocuments serviceEmpruntDocuments;
    private ServiceReservation serviceReservation;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammeTp3BiblioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nCRUD - CD");
        var cd = new CD(EtatDocument.DISPONIBLE,
                Document.C_CD,
                "harry potter",
                "JK. Rolling",
                "maison edition",
                2000,
                "classique",
                "JK. Rolling",
                "michel");

        serviceDocument.saveCD(cd);
        var cd2 = serviceDocument.getCD(cd.getId());
        System.out.println(cd2);

        /*cd.setGenreMusique("jazz");
        serviceDocument.updateCD(cd);
        var cd3 = serviceDocument.getCD(cd.getId());
        System.out.println(cd3);*/

        var cd4 = serviceDocument.getCD(cd.getId());
        serviceDocument.removeCD(cd);
        System.out.println(cd4);




        System.out.println("\nCRUD - DVD");
        var dvd = new DVD(
                EtatDocument.ENDOMMAGE,
                Document.C_DVD,
                "bobby bob",
                "lilo lee",
                "edition bop",
                2018,
                44,
                "drame");

        serviceDocument.saveDVD(dvd);
        var dvd2 = serviceDocument.getDVD(dvd.getId());
        System.out.println(dvd2);

        /*dvd.setEtatDocument(EtatDocument.DISPONIBLE);
        serviceDocument.updateDVD(dvd);
        var dvd3 = serviceDocument.getDVD(dvd.getId());
        System.out.println(dvd3);*/

        var dvd4 = serviceDocument.getDVD(dvd.getId());
        serviceDocument.removeDVD(dvd);
        System.out.println(dvd4);





        System.out.println("\nCRUD - Livre");
        var livre = new Livre(
                EtatDocument.EMPRUNTE,
                Document.C_LIVRE,
                "avengers",
                "Josh whedon",
                "marvel",
                2020,
                230,
                GenreLivre.ROMAN);
        serviceDocument.saveLivre(livre);
        var livre2 = serviceDocument.getLivre(livre.getId());
        System.out.println(livre2);

        /*livre.setNbrPages(900);
        serviceDocument.updateLivre(livre);
        var livre3 = serviceDocument.getLivre(livre.getId());
        System.out.println(livre3);*/

        var livre4 = serviceDocument.getLivre(livre.getId());
        serviceDocument.removeLivre(livre);
        System.out.println(livre4);




        System.out.println("\nCRUD - Employe");
        var employe = new Employe(
                "bernadette",
                "carmier",
                Fonction.GESTIONNAIRE);
        serviceEmploye.saveEmploye(employe);
        var employe2 = serviceEmploye.getEmploye(employe.getId());
        System.out.println(employe2);

        /*employe.setFonction(Fonction.PREPOSE);
        serviceEmploye.updateEmploye(employe);
        var employe3 = serviceEmploye.getEmploye(employe.getId());
        System.out.println(employe3);*/

        var employe4 = serviceEmploye.getEmploye(employe.getId());
        serviceEmploye.removeEmploye(employe);
        System.out.println(employe4);



        System.out.println("\nCRUD - Client");
        var client = new Client(
                "John",
                "Smith",
                "Daragon",
                "Montreal",
                "H05C42",
                "514-900-5698",
                new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2022"),
                1);

        serviceClient.saveClient(client);
        var client2 = serviceClient.getClient(client.getId());
        System.out.println(client2);

        /*client.setRue("Drolet");
        serviceClient.updateClient(client);
        var client3 = serviceClient.getClient(client.getId());
        System.out.println(client3);*/

        //Delete du client à la fin des opérations




        System.out.println("\nCRUD - Reservation");
        livre = new Livre(
                EtatDocument.EMPRUNTE,
                Document.C_LIVRE,
                "avengers",
                "Josh whedon",
                "marvel",
                2020,
                230,
                GenreLivre.ROMAN);
        serviceDocument.saveLivre(livre);
        livre2 = serviceDocument.getLivre(livre.getId());
        System.out.println(livre2);

        var reservation = new Reservation(
                new SimpleDateFormat("dd/MM/yyyy").parse("05/10/2000"),
                client,
                livre);
        serviceReservation.saveReservation(reservation);
        var reservation2 = serviceReservation.getReservation(reservation.getId());
        System.out.println(reservation2);

        /*reservation.setDateReservation(new SimpleDateFormat("dd/MM/yyyy").parse("13/03/2022"));
        serviceReservation.updateReservation(reservation);
        var reservation3 = serviceReservation.getReservation(reservation.getId());
        System.out.println(reservation3);*/

        var reservation4 = serviceReservation.getReservation(reservation.getId());
        serviceReservation.removeReservation(reservation);
        System.out.println(reservation4);




        System.out.println("\nCRUD - EmpruntDocuments");
        dvd = new DVD(
                EtatDocument.ENDOMMAGE,
                Document.C_DVD,
                "bobby bob",
                "lilo lee",
                "edition bop",
                2018,
                44,
                "drame");

        serviceDocument.saveDVD(dvd);
        dvd2 = serviceDocument.getDVD(dvd.getId());
        System.out.println(dvd2);

        var empruntDocuments = new EmpruntDocuments(
                new SimpleDateFormat("dd/MM/yyyy").parse("15/03/2018"),
                new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2022"),
                2,
                client,
                dvd);
        serviceEmpruntDocuments.saveEmpruntDocuments(empruntDocuments);
        var empruntDocuments2 = serviceEmpruntDocuments.getEmpruntDocuments(empruntDocuments.getId());
        System.out.println(empruntDocuments2);

        /*empruntDocuments.setNbrRappel(0);
        serviceEmpruntDocuments.updateEmpruntDocuments(empruntDocuments);
        var empruntDocuments3 = serviceEmpruntDocuments.getEmpruntDocuments(empruntDocuments.getId());
        System.out.println(empruntDocuments3);*/

        var empruntDocuments4 = serviceEmpruntDocuments.getEmpruntDocuments(empruntDocuments.getId());
        serviceEmpruntDocuments.removeEmpruntDocuments(empruntDocuments);
        System.out.println(empruntDocuments4);




        /*System.out.println("\nLISTE DOCUMENTS :");
        List<Document> listeDocuments = serviceDocument.rechercheDocument("",
                EtatDocument.ENDOMMAGE,
                "bob",
                "",
                "",
                0);
        listeDocuments.forEach((document) -> {
            System.out.println(document.toStringDocument());
        });
        System.out.println();*/



        /*System.out.println("\nFaire un emprunt");
        System.out.println(serviceEmpruntDocuments.faireEmprunt(client, livre));*/



        /*System.out.println("\nNOMBRE D'EMPRUNT PAR MOIS :");
        Long[] nbrEmpruntParMois = serviceEmpruntDocuments.getNbrEmpruntParMois();
        for (int i = 0; i < nbrEmpruntParMois.length; i++) {
            System.out.println(new DateFormatSymbols().getMonths()[i] + "  " + nbrEmpruntParMois[i]);
        }
        System.out.println();*/


        /*System.out.println("\nListe des emprunts du client:");
        var clientEmprunt = serviceEmpruntDocuments.getClientEmprunt(client.getId());
        for (EmpruntDocuments empruntDocument: clientEmprunt) {
            System.out.println(empruntDocument);
        }
        System.out.println();*/



        /*System.out.println("\nDelete empruntDocument");
        serviceEmpruntDocuments.removeEmpruntDocuments(clientEmprunt.get(0));
        System.out.println(clientEmprunt.get(0));*/




        System.out.println("\nDelete client");
        var client4 = serviceClient.getClient(client.getId());
        serviceClient.removeClient(client);
        System.out.println(client4);
    }
}
