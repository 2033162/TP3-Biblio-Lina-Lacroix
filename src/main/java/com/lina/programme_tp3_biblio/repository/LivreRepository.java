package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
