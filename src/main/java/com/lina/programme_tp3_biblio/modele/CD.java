package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CD")
@Data
@NoArgsConstructor
public class CD extends Document {
    private String genreMusique;
    private String compositeur;
    private String interprete;

    public CD(EtatDocument etatDocument, String genreDocument, String titre, String auteur, String editeur, int anneePublication, String genreMusique, String compositeur, String interprete) {
        this.setEtatDocument(etatDocument);
        this.setGenreDocument(genreDocument);
        this.setTitre(titre);
        this.setAuteur(auteur);
        this.setEditeur(editeur);
        this.setAnneePublication(anneePublication);
        this.genreMusique = genreMusique;
        this.compositeur = compositeur;
        this.interprete = interprete;
    }

    @Override
    public String toString() {
        return "CD{" +
                getDocument().toStringDocument() + '\n' +
                "genreMusique='" + genreMusique + '\'' +
                ", compositeur='" + compositeur + '\'' +
                ", interprete='" + interprete + '\'' +
                '}';
    }
}