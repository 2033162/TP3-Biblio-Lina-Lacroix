package com.lina.programme_tp3_biblio.modele;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Fonction {
    GESTIONNAIRE("GESTIONNAIRE"),
    PREPOSE("PREPOSE");

    private String nomFonction;
    Fonction(String nomFonction) {
        this.nomFonction = nomFonction;
    }

    @Override
    public String toString() {
        return nomFonction;
    }

    public static Fonction get(String nomFonction) {
        Stream<Fonction> values = Arrays.stream(Fonction.values());
        values = values.filter(fieldTag -> fieldTag.nomFonction.equalsIgnoreCase(nomFonction));
        if (values == null) {
            return null;
        } else {
            return values.findFirst().get();
        }
    }
}