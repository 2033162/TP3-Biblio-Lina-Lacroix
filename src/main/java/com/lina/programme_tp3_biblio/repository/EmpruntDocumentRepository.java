package com.lina.programme_tp3_biblio.repository;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpruntDocumentRepository extends JpaRepository<EmpruntDocuments, Long> {
    @Query(value = "SELECT e FROM EmpruntDocuments e WHERE e.client.id = :clientId")
    List<EmpruntDocuments> getClientEmprunt(@Param("clientId") long clientId);

    @Query(value = "SELECT MONTH(date_Initial) AS mois, COUNT(*) AS nbr_emprunt " +
            "from Emprunt_Documents e GROUP BY MONTH(date_Initial) ORDER BY MONTH(date_Initial)", nativeQuery = true)
    List<Object[]> getNbrEmpruntParMois();
}