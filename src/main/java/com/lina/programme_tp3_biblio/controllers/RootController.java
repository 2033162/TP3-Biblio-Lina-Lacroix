package com.lina.programme_tp3_biblio.controllers;

import com.lina.programme_tp3_biblio.service.ServiceClient;
import com.lina.programme_tp3_biblio.service.ServiceDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ServiceClient serviceClient;
    private ServiceDocument serviceDocument;

    public RootController(ServiceClient serviceClient, ServiceDocument serviceDocument) {
        this.serviceClient = serviceClient;
        this.serviceDocument = serviceDocument;
    }

    @GetMapping("/")
    public String getRootRequest(Model model) {
        model.addAttribute("pageTitle1", "Ma bibliotheque");
        model.addAttribute("h1Text", "La librairie de la ville de Javatown");
        return "index";
    }
}
