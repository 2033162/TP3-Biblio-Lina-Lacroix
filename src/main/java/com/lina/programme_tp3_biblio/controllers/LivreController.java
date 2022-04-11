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
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LivreController {
    Logger logger = LoggerFactory.getLogger(LivreController.class);

    private ServiceDocument serviceDocument;

    public LivreController(ServiceDocument serviceDocument) {
        this.serviceDocument = serviceDocument;
    }

    @GetMapping("/")
    public String getRootRequest(Model model) {
        model.addAttribute("pageTitle1", "Ma bibliotheque");
        model.addAttribute("h1Text", "La librairie de la ville de Javatown");
        return "index";
    }

    @GetMapping("/livres")
    public String getLivres(Model model) {
        model.addAttribute("pageTitle", "Ma bibliotheque");
        var livres = serviceDocument.findAllLivres();
        model.addAttribute("livres", livres);
        return "livres";
    }

    @GetMapping("/crudlivre")
    public String getLivreRequest(HttpServletRequest request,
                                   Model model) {
        model.addAttribute("pageTitle", "Livre");
        var livre = new Livre();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);;
        if (inputFlashMap != null)
            livre = (Livre) inputFlashMap.get("livre");
        model.addAttribute("livre", livre);
        return "crudlivre";
    }

    @GetMapping("/livreedit/{id}")
    public String getLivreRequest(HttpServletRequest request,
                                   Model model,
                                   @PathVariable(required = false) Long livreId) {
        model.addAttribute("pageTitle", "Livre");
        var livre = new Livre();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null)
            livre = (Livre) inputFlashMap.get("livre");
        model.addAttribute("livre", livre);
        return "livreedit";
    }

    @GetMapping("/livrecreate")
    public String getLivreCreate(@ModelAttribute LivreForm livreForm,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        livreForm = new LivreForm(new Livre());
        model.addAttribute("livreForm", livreForm);
        return "livreedit";
    }

    @PostMapping("/livrecreate")
    public String livrePost(@ModelAttribute LivreForm livreForm,
                             BindingResult errors,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        logger.info("livre: " + livreForm);
        serviceDocument.saveLivre(livreForm.toLivre());
        redirectAttributes.addFlashAttribute("livreForm", livreForm);
        model.addAttribute("pageTitle", "Livre");
        model.addAttribute("livreForm", livreForm);
        return "redirect:livreedit/" + livreForm.getId();
    }
}