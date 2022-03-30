package com.lina.programme_tp3_biblio.repository;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntDocumentRepository extends JpaRepository<EmpruntDocuments, Long> {
}
