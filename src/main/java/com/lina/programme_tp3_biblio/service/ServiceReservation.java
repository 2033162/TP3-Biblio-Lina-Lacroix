package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.Reservation;
import com.lina.programme_tp3_biblio.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ServiceReservation {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation saveReservation(Date dateReservation, Client client, Document document) {
        return reservationRepository.save(new Reservation(dateReservation, client, document));
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public Reservation getReservation(long reservationID) {
        return reservationRepository.getById(reservationID);
    }
}
