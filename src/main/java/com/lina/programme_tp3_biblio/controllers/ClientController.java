package com.lina.programme_tp3_biblio.controllers;

import com.lina.programme_tp3_biblio.forms.ClientForm;
import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.service.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ClientController {
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ServiceClient serviceClient;

    public ClientController(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping(value = {"/", "/index", "index.html"})
    public String getRootRequest(Model model) {
        model.addAttribute("pageTitle", "Ma bibliotheque");
        model.addAttribute("h1Text", "La librairie de la ville de Javatown");
        return "index";
    }

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("pageTitle", "Ma bibliotheque");
        var clients = serviceClient.findAllClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping(value = {"/clientcreate"})
    public String getClientCreate(Model model) {
        var clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);
        return "/clientedit";
    }

    @PostMapping(value = {"/clientcreate"})
    public RedirectView clientPost(@ModelAttribute ClientForm clientForm,
                                   BindingResult errors,
                                   RedirectAttributes redirectAttributes) {
        logger.info("client: " + clientForm);
        final Client client = serviceClient.saveClient(clientForm.toClient());
        clientForm.setId(Long.toString(client.getId()));

        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        redirectAttributes.addAttribute("id", clientForm.getId());

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/clientedit/{id}");
        return redirectView;
    }

    @GetMapping(value = {"/clientedit/{id}"})
    public String getClientRequest(@ModelAttribute ClientForm clientForm,
                                   Model model,
                                   @PathVariable("id") String id) {
        logger.info("Into getClientRequest with id " + id);
        long clientId = getIdFromString(id);
        final Optional<Client> clientById = serviceClient.getClient(clientId);
        clientForm = new ClientForm();
        if (clientById.isPresent()) {
            clientForm = new ClientForm(clientById.get());
        }
        model.addAttribute("clientForm", clientForm);
        return "/clientedit";
    }

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}