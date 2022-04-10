package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.*;
import com.lina.programme_tp3_biblio.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDocument {
    @Autowired
    private DocumentRepository documentRepository;

    public CD saveCD(EtatDocument etatDocument,
                     String genreDocument,
                     String titre,
                     String auteur,
                     String editeur,
                     int anneePublication,
                     String genreMusique,
                     String compositeur,
                     String interprete) {
        return documentRepository.save(new CD(etatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                genreMusique,
                compositeur,
                interprete
                ));
    }

    public CD saveCD(CD cd) {
        return documentRepository.save(cd);
    }

    public void removeCD(CD cd) {
        documentRepository.delete(cd);
    }

    public CD getCD(long cdID) {
        return null;
        //return documentRepository.getById(cdID);
    }

    public DVD saveDVD(EtatDocument etatDocument,
                       String genreDocument,
                       String titre,
                       String auteur,
                       String editeur,
                       int anneePublication,
                       int duree,
                       String genreFilm) {
        return documentRepository.save(new DVD(etatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                duree,
                genreFilm));
    }

    public DVD saveDVD(DVD dvd) {
        return documentRepository.save(dvd);
    }

    public void removeDVD(DVD dvd) {
        documentRepository.delete(dvd);
    }

    public DVD getDVD(long dvdID) {
        return null;
        //return documentRepository.getById(dvdID);
    }

    public Livre saveLivre(EtatDocument etatDocument,
                           String genreDocument,
                           String titre,
                           String auteur,
                           String editeur,
                           int anneePublication,
                           int nbrPages,
                           GenreLivre genreLivre) {
        return documentRepository.save(new Livre(etatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                nbrPages,
                genreLivre));
    }

    public Livre saveLivre(Livre livre) {
        return documentRepository.save(livre);
    }

    public void removeLivre(Livre livre) {
        documentRepository.delete(livre);
    }

    public Livre getLivre(long livreId) {
        return null;
        //return documentRepository.getById(livreId);
    }

    public List<Document> findAllLivres() {
        return documentRepository.findAll();
    }

    /*public List<Document> searchDocument(String genreDocument,
                                         EtatDocument etatDocument,
                                         String titre,
                                         String auteur,
                                         String editeur,
                                         int anneePublication) {
        return documentRepository.searchDocument(genreDocument,
                etatDocument,
                titre,
                auteur,
                editeur,
                anneePublication);
    }*/
}
