package com.lina.programme_tp3_biblio.service;

import com.lina.programme_tp3_biblio.repository.AmendeRepository;
import org.springframework.stereotype.Component;

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
