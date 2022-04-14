package com.lina.programme_tp3_biblio.repository;


import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT d FROM Document d WHERE LOWER(concat('%', d.titre, '%')) like LOWER(concat('%', :titre, '%')) and LOWER(d.auteur) = LOWER(:auteur) and LOWER(d.anneePublication) = LOWER(:anneePublication) and LOWER(d.genreDocument) = LOWER(:genreDocument)")
    Optional<Document> searchDocument(@Param("titre") String titre,
                                      @Param("auteur") String auteur,
                                      @Param("anneePublication") int anneePublication,
                                      @Param("genreDocument") String genreDocument);

    /*
    public List<Documents> rechercheDocument(String genreDocument, EtatDocument etatDocument, String titre, String auteur, String editeur, int anneePublication) {
        String where = "";

        if (!titre.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.titre like '%" + titre + "%')";
        }

        if (!auteur.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.auteur='" + auteur + "')";
        }

        if (anneePublication != 0) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.anneePublication=" + anneePublication + ")";
        }

        if (!genreDocument.trim().equals("")) {
            where += (where.equals("") ? "" : " AND ");
            where += " (d.genreDocument='" + genreDocument + "')";
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
