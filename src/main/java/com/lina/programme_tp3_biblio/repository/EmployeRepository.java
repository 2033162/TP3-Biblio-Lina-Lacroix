package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
