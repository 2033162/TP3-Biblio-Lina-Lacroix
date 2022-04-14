package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Amende {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client")
    @ToString.Exclude
    private Client client;

    private double unJourAmende = 0.25;
    private long sommeAmende;

    public Amende(Client client, long unJourAmende, long sommeAmende) {
        this.client = client;
        this.unJourAmende = unJourAmende;
        this.sommeAmende = sommeAmende;
    }
}