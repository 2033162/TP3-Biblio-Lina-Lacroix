package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.Reservation;
import com.lina.programme_tp3_biblio.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ServiceReservation {

    private ReservationRepository reservationRepository;

    public ServiceReservation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

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