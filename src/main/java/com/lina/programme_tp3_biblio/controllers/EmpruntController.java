package com.lina.programme_tp3_biblio.controllers;

import com.lina.programme_tp3_biblio.forms.EmpruntForm;
import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.modele.Document;
import com.lina.programme_tp3_biblio.modele.EmpruntDocuments;
import com.lina.programme_tp3_biblio.service.ServiceClient;
import com.lina.programme_tp3_biblio.service.ServiceDocument;
import com.lina.programme_tp3_biblio.service.ServiceEmpruntDocuments;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
public class EmpruntController {
    Logger logger = LoggerFactory.getLogger(EmpruntController.class);

    private ServiceEmpruntDocuments serviceEmpruntDocuments;
    private ServiceClient serviceClient;
    private ServiceDocument serviceDocument;

    public EmpruntController(ServiceEmpruntDocuments serviceEmpruntDocuments,
                             ServiceClient serviceClient,
                             ServiceDocument serviceDocument) {
        this.serviceEmpruntDocuments = serviceEmpruntDocuments;
        this.serviceClient = serviceClient;
        this.serviceDocument = serviceDocument;
    }

    @GetMapping("/emprunt")
    public String getEmprunts(Model model) {
        model.addAttribute("pageTitle", "Ma bibliotheque");
        var emprunts = serviceEmpruntDocuments.findAllEmpruntDocuments();
        model.addAttribute("emprunts", emprunts);
        return "emprunts";
    }

    @GetMapping(value = {"/empruntcreate"})
    public String getEmpruntCreate(Model model) {
        var empruntForm = new EmpruntForm();
        model.addAttribute("empruntForm", empruntForm);
        return "/empruntedit";
    }

    @PostMapping(value = {"/empruntcreate"})
    public RedirectView empruntPost(@ModelAttribute EmpruntForm empruntForm,
                                    BindingResult errors,
                                    RedirectAttributes redirectAttributes) throws ParseException {
        logger.info("emprunt: " + empruntForm);

        Client client = serviceClient.findByName(empruntForm.getClient());
        List<Document> documents = serviceDocument.searchDocument(empruntForm.getTitre(), empruntForm.getAuteur(),
                Integer.valueOf(empruntForm.getAnneePublication()), "livre");

        final EmpruntDocuments empruntDocuments = serviceEmpruntDocuments.saveEmpruntDocuments(
                new SimpleDateFormat("yyyy/mm/dd").parse(empruntForm.getDateInitial()),
                new SimpleDateFormat("yyyy/mm/dd").parse(empruntForm.getDateExpire()),
                empruntForm.getNbrRappel(),
                client,
                documents.get(0));
        empruntForm.setId(Long.toString(empruntDocuments.getId()));

        redirectAttributes.addFlashAttribute("empruntForm", empruntForm);
        redirectAttributes.addAttribute("id", empruntForm.getId());

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/empruntedit/{id}");
        return redirectView;
    }

    @GetMapping(value = {"/empruntedit/{id}"})
    public String getEmpruntRequest(@ModelAttribute EmpruntForm empruntForm,
                                    Model model,
                                    @PathVariable("id") String id) {
        logger.info("Into getEmpruntRequest with id " + id);
        long empruntId = getIdFromString(id);
        final Optional<EmpruntDocuments> empruntById = serviceEmpruntDocuments.getEmpruntDocuments(empruntId);
        empruntForm = new EmpruntForm();
        if (empruntById.isPresent()) {
            empruntForm = new EmpruntForm(empruntById.get());
        }
        model.addAttribute("empruntForm", empruntForm);
        return "/empruntedit";
    }

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}
