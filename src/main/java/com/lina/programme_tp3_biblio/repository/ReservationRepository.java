package com.lina.programme_tp3_biblio.repository;

import com.lina.programme_tp3_biblio.modele.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
