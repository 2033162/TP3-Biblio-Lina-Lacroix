package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.*;
import com.lina.programme_tp3_biblio.repository.CdRepository;
import com.lina.programme_tp3_biblio.repository.DocumentRepository;
import com.lina.programme_tp3_biblio.repository.DvdRepository;
import com.lina.programme_tp3_biblio.repository.LivreRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceDocument {

    private DocumentRepository documentRepository;
    private CdRepository cdRepository;
    private DvdRepository dvdRepository;
    private LivreRepository livreRepository;

    public ServiceDocument(DocumentRepository documentRepository,
                           CdRepository cdRepository,
                           DvdRepository dvdRepository,
                           LivreRepository livreRepository) {
        this.documentRepository = documentRepository;
        this.cdRepository = cdRepository;
        this.dvdRepository = dvdRepository;
        this.livreRepository = livreRepository;
    }

    public CD saveCD(EtatDocument etatDocument,
                     String genreDocument,
                     String titre,
                     String auteur,
                     String editeur,
                     int anneePublication,
                     String genreMusique,
                     String compositeur,
                     String interprete) {
        return cdRepository.save(new CD(etatDocument,
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
        return cdRepository.save(cd);
    }

    public void removeCD(CD cd) {
        cdRepository.delete(cd);
    }

    public Optional<CD> getCD(long cdID) {
        return cdRepository.findById(cdID);
    }

    public DVD saveDVD(EtatDocument etatDocument,
                       String genreDocument,
                       String titre,
                       String auteur,
                       String editeur,
                       int anneePublication,
                       int duree,
                       String genreFilm) {
        return dvdRepository.save(new DVD(etatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                duree,
                genreFilm));
    }

    public DVD saveDVD(DVD dvd) {
        return dvdRepository.save(dvd);
    }

    public void removeDVD(DVD dvd) {
        dvdRepository.delete(dvd);
    }

    public Optional<DVD> getDVD(long dvdID) {
        return dvdRepository.findById(dvdID);
    }

    public Livre saveLivre(EtatDocument etatDocument,
                           String genreDocument,
                           String titre,
                           String auteur,
                           String editeur,
                           int anneePublication,
                           int nbrPages,
                           GenreLivre genreLivre) {
        return livreRepository.save(new Livre(etatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                nbrPages,
                genreLivre));
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void removeLivre(Livre livre) {
        livreRepository.delete(livre);
    }

    public Optional<Livre> getLivre(long livreId) {
        return livreRepository.findById(livreId);
    }

    public List<Livre> findAllLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> findLivreById(long id) {
        return livreRepository.findById(id);
    }

    public List<Document> searchDocument(String titre,
                                             String auteur,
                                             int anneePublication,
                                             String genreDocument) {
        return documentRepository.searchDocument(titre,
                auteur,
                anneePublication,
                genreDocument);
    }
}