package com.lina.programme_tp3_biblio.repository;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpruntDocumentRepository extends JpaRepository<EmpruntDocuments, Long> {

    @Query(value= "SELECT MONTH(dateInitial) AS mois, COUNT(*) AS nbr_emprunt from EmpruntDocuments e WHERE e.id = :empruntDocumentId GROUP BY MONTH(dateInitial) ORDER BY MONTH(dateInitial)")
    Optional<Object[]> empruntParMois(@Param("mois") long empruntDocumentId);
}