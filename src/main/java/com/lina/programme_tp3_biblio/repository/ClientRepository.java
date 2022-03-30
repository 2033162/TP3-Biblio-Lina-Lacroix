package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
