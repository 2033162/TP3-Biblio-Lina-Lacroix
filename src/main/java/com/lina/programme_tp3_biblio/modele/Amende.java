package com.lina.programme_tp3_biblio.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Amende {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date dateInitial;
    private Date dateExpire;
    private int nbrRappel;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "document")
    private Document document;
    private double sommeAmende;

    public Amende(Date dateInitial, Date dateExpire, int nbrRappel, Document document, Client client, double sommeAmende) {
        this.dateInitial = dateInitial;
        this.dateExpire = dateExpire;
        this.nbrRappel = nbrRappel;
        this.document = document;
        this.client = client;
        this.sommeAmende = sommeAmende;
    }
}