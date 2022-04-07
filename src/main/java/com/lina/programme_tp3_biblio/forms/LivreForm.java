package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.EtatDocument;
import com.lina.programme_tp3_biblio.modele.GenreLivre;
import com.lina.programme_tp3_biblio.modele.Livre;
import lombok.Data;

@Data
public class LivreForm {
    private String id;
    private String etatDocument;
    private String genreDocument;
    private String titre;
    private String auteur;
    private String editeur;
    private int anneePublication;
    private int nbrPages;
    private String genreLivre;

    public LivreForm(String id,
                     String etatDocument,
                     String genreDocument,
                     String titre,
                     String auteur,
                     String editeur,
                     int anneePublication,
                     int nbrPages,
                     String genreLivre) {
        this.id = id;
        this.etatDocument = etatDocument;
        this.genreDocument = genreDocument;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneePublication = anneePublication;
        this.nbrPages = nbrPages;
        this.genreLivre = genreLivre;
    }

    public LivreForm() {
    }

    public LivreForm(Livre livre) {
        this(Long.toString(livre.getId()),
                null,
                livre.getGenreDocument(),
                livre.getTitre(),
                livre.getAuteur(),
                livre.getEditeur(),
                livre.getAnneePublication(),
                livre.getNbrPages(),
                null);
    }

    public Livre toLivre() {
        EtatDocument etatDocument = null;
        GenreLivre genreLivre = null;
        return new Livre(etatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                nbrPages,
                genreLivre);
    }
}