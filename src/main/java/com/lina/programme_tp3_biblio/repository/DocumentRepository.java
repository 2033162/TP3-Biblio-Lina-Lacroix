package com.lina.programme_tp3_biblio.repository;


import com.lina.programme_tp3_biblio.modele.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
