package com.lina.programme_tp3_biblio.modele;

import java.util.Arrays;
import java.util.stream.Stream;

public enum EtatDocument {
    DISPONIBLE("DISPONIBLE"),
    RESERVE("RESERVE"),
    EMPRUNTE("EMPRUNTE"),
    ENDOMMAGE("ENDOMMAGE");

    private String nomEtatDocument;
    EtatDocument(String nomEtatDocument) {
        this.nomEtatDocument = nomEtatDocument;
    }

    @Override
    public String toString(){
        return nomEtatDocument;
    }

    public static EtatDocument get(String nomEtatDocument) {
        Stream<EtatDocument> values = Arrays.stream(EtatDocument.values());
        values = values.filter(fieldTag -> fieldTag.nomEtatDocument.equalsIgnoreCase(nomEtatDocument));
        if (values == null) {
            return null;
        } else {
            return values.findFirst().get();
        }
    }
}

