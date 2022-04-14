package com.lina.programme_tp3_biblio.repository;


import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT d FROM Document d WHERE LOWER(d.titre) like LOWER(concat('%', :titre, '%')) and LOWER(d.auteur) = LOWER(:auteur) and LOWER(d.anneePublication) = LOWER(:anneePublication) and LOWER(d.genreDocument) = LOWER(:genreDocument)")
    List<Document> searchDocument(@Param("titre") String titre,
                                  @Param("auteur") String auteur,
                                  @Param("anneePublication") int anneePublication,
                                  @Param("genreDocument") String genreDocument);
}
