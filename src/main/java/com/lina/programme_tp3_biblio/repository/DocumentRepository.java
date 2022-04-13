package com.lina.programme_tp3_biblio.repository;


import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT d FROM Document d WHERE UPPER(d.genreDocument) = UPPER(:genreDocument) and UPPER(d.etatDocument) = UPPER(:etatDocument) and UPPER(d.titre) = UPPER(:titre) and UPPER(d.auteur) = UPPER(:auteur) and UPPER(d.editeur) = UPPER(:editeur) and UPPER(d.anneePublication) = UPPER(:anneePublication)")
    Optional<Document> searchDocument(@Param("") String genreDocument,
                                      @Param("") EtatDocument etatDocument,
                                      @Param("") String titre,
                                      @Param("") String auteur,
                                      @Param("") String editeur,
                                      @Param("") int anneePublication);

    /*
    public List<Documents> rechercheDocument(String genreDocument, EtatDocument etatDocument, String titre, String auteur, String editeur, int anneePublication) {
        String where = "";
        if (!genreDocument.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.genreDocument='" + genreDocument + "')";
        }

        if (!titre.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.titre like '%" + titre + "%')";
        }

        if (!auteur.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.auteur='" + auteur + "')";
        }

        if (!editeur.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.editeur='" + editeur + "')";
        }

        if (anneePublication != 0) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.anneePublication=" + anneePublication + ")";
        }

        where =  " WHERE " + where;
        String query = "select d from Documents d" + where;

        final EntityManager em = emf.createEntityManager();

        List<Documents> documents = em.createQuery(query).getResultList();

        em.close();
        return documents;
    }
    */
}
