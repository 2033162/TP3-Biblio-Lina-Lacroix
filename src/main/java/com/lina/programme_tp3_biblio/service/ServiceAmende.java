package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.modele.Amende;
import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.repository.AmendeRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ServiceAmende {

    private AmendeRepository amendeRepository;

    public ServiceAmende(AmendeRepository amendeRepository) {
        this.amendeRepository = amendeRepository;
    }

    public void deleteAllAmende() {
        amendeRepository.deleteAll();
    }
}
