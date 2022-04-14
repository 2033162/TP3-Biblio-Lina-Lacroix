package com.lina.programme_tp3_biblio.controllers;

import com.lina.programme_tp3_biblio.forms.LivreForm;
import com.lina.programme_tp3_biblio.modele.Livre;
import com.lina.programme_tp3_biblio.service.ServiceDocument;
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
public class LivreController {
    Logger logger = LoggerFactory.getLogger(LivreController.class);

    private ServiceDocument serviceDocument;

    public LivreController(ServiceDocument serviceDocument) {
        this.serviceDocument = serviceDocument;
    }

    @GetMapping("/livres")
    public String getLivres(Model model) {
        model.addAttribute("pageTitle", "Ma bibliotheque");
        var livres = serviceDocument.findAllLivres();
        model.addAttribute("livres", livres);
        return "livres";
    }

    @GetMapping(value = {"/livrecreate"})
    public String getLivreCreate(Model model) {
        var livreForm = new LivreForm();
        model.addAttribute("livreForm", livreForm);
        return "/livreedit";
    }

    @PostMapping(value = {"/livrecreate"})
    public RedirectView livrePost(@ModelAttribute LivreForm livreForm,
                                   BindingResult errors,
                                   RedirectAttributes redirectAttributes) {
        logger.info("livre: " + livreForm);
        final Livre livre = serviceDocument.saveLivre(livreForm.toLivre());
        livreForm.setId(Long.toString(livre.getId()));

        redirectAttributes.addFlashAttribute("livreForm", livreForm);
        redirectAttributes.addAttribute("id", livreForm.getId());

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/livreedit/{id}");
        return redirectView;
    }

    @GetMapping(value = {"/livreedit/{id}"})
    public String getLivreRequest(@ModelAttribute LivreForm livreForm,
                                   Model model,
                                   @PathVariable("id") String id) {
        logger.info("Into getLivreRequest with id " + id);
        long livreId = getIdFromString(id);
        final Optional<Livre> livreById = serviceDocument.findLivreById(livreId);
        livreForm = new LivreForm();
        if (livreById.isPresent()) {
            livreForm = new LivreForm(livreById.get());
        }
        model.addAttribute("livreForm", livreForm);
        return "/livreedit";
    }

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}