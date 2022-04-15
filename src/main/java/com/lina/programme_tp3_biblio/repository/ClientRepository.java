package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * FROM Client c WHERE LOWER(c.nom + ' ' + c.prenom) = LOWER(:nomPrenom)", nativeQuery = true)
    Client findByName(@Param("nomPrenom") String nomPrenom);
}
