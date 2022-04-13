package com.lina.programme_tp3_biblio.repository;


import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT d FROM Document d WHERE LOWER(d.genreDocument) = LOWER(:genreDocument) and LOWER(d.etatDocument) = LOWER(:etatDocument) and LOWER(d.titre) = LOWER(:titre) and LOWER(d.auteur) = LOWER(:auteur) and LOWER(d.editeur) = LOWER(:editeur) and LOWER(d.anneePublication) = LOWER(:anneePublication)")
    Optional<Document> searchDocument(@Param("genreDocument") String genreDocument,
                                      @Param("etatDocument") EtatDocument etatDocument,
                                      @Param("titre") String titre,
                                      @Param("auteur") String auteur,
                                      @Param("editeur") String editeur,
                                      @Param("anneePublication") int anneePublication);

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
