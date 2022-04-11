package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Livre")
@Data
@NoArgsConstructor
public class Livre extends Document {
    private int nbrPages;
    private GenreLivre genreLivre;

    public Livre(EtatDocument etatDocument, String genreDocument, String titre, String auteur, String editeur, int anneePublication, int nbrPages, GenreLivre genreLivre) {
        this.setEtatDocument(etatDocument);
        this.setGenreDocument(genreDocument);
        this.setTitre(titre);
        this.setAuteur(auteur);
        this.setEditeur(editeur);
        this.setAnneePublication(anneePublication);
        this.nbrPages = nbrPages;
        this.genreLivre = genreLivre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                getDocument().toStringDocument() + '\n' +
                "nbrPages=" + nbrPages +
                ", genreLivre='" + genreLivre + '\'' +
                '}';
    }
}