package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.DVD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvdRepository extends JpaRepository<DVD, Long> {
}
