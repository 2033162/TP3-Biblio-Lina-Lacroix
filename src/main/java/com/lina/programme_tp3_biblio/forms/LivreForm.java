package com.lina.programme_tp3_biblio.forms;

import com.lina.programme_tp3_biblio.modele.EtatDocument;
import com.lina.programme_tp3_biblio.modele.GenreLivre;
import com.lina.programme_tp3_biblio.modele.Livre;
import lombok.Data;

@Data
public class LivreForm {
    private static EtatDocument ETATDOCUMENT;
    private static GenreLivre GENRELIVRE;
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
        this(new Livre());
    }

    public LivreForm(Livre livre) {
        this(Long.toString(livre.getId()),
                livre.getEtatDocument() == null ? null : String.valueOf(EtatDocument.valueOf(livre.getEtatDocument().toString())),
                livre.getGenreDocument(),
                livre.getTitre(),
                livre.getAuteur(),
                livre.getEditeur(),
                livre.getAnneePublication(),
                livre.getNbrPages(),
                livre.getGenreLivre() == null ? null : String.valueOf(GenreLivre.valueOf(livre.getGenreLivre().toString())));
    }

    public Livre toLivre() {
        EtatDocument bEtatDocument;
        GenreLivre bGenreLivre;
        try {
            bEtatDocument = etatDocument == null ? null : EtatDocument.valueOf(etatDocument);
            bGenreLivre = genreLivre == null ? null : GenreLivre.valueOf(genreLivre);
        } catch (Exception e) {
            bEtatDocument = null;
            bGenreLivre = null;
        }
        final Livre livre = new Livre(bEtatDocument,
                genreDocument,
                titre,
                auteur,
                editeur,
                anneePublication,
                nbrPages,
                bGenreLivre);
        long oldId;
        try {
            oldId = Long.parseLong(id);
            if (oldId > 0)
                livre.setId(oldId);
        } catch (NumberFormatException e) {}

        return livre;
    }
}