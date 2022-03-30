package com.lina.programme_tp3_biblio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lina.programme_tp3_biblio.service.*;

@SpringBootApplication
public class ProgrammeTp3BiblioApplication implements CommandLineRunner {

    @Autowired
    private ServiceClient serviceClient;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammeTp3BiblioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
