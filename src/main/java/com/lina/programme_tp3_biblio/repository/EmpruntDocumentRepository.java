package com.lina.programme_tp3_biblio.repository;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpruntDocumentRepository extends JpaRepository<EmpruntDocuments, Long> {
    @Query(value = "SELECT e FROM EmpruntDocuments e WHERE e.client.id = :clientId")
    Optional<EmpruntDocuments> getClientEmprunt(@Param("clientId") long clientId);

    //error
    @Query(value = "SELECT MONTH(dateInitial) = :mois, COUNT(*) = :nbr_emprunt from EmpruntDocuments e GROUP BY MONTH(dateInitial) ORDER BY MONTH(dateInitial)")
    List<Object[]> getNbrEmpruntParMois();
}