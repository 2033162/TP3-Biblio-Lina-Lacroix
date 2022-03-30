package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("DVD")
@Data
@NoArgsConstructor
public class DVD extends Document {
    private int duree;
    private String genreFilm;

    public DVD(EtatDocument etatDocument, String genreDocument, String titre, String auteur, String editeur, int anneePublication, int duree, String genreFilm) {
        this.setEtatDocument(etatDocument);
        this.setGenreDocument(genreDocument);
        this.setTitre(titre);
        this.setAuteur(auteur);
        this.setEditeur(editeur);
        this.setAnneePublication(anneePublication);
        this.duree = duree;
        this.genreFilm = genreFilm;
    }

    @Override
    public String toString() {
        return "DVD{" +
                getDocument().toStringDocument() + '\n' +
                "duree=" + duree +
                ", genreFilm='" + genreFilm + '\'' +
                '}';
    }
}
