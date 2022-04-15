package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.Amende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmendeRepository extends JpaRepository<Amende, Long> {

    @Query(value = "SELECT a FROM Amende a WHERE a.client.id = :clientId")
    List<Amende> getClientAmendes(long clientId);
}
