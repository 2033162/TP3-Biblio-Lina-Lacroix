package com.lina.programme_tp3_biblio.controllers;

import com.lina.programme_tp3_biblio.forms.ClientForm;
import com.lina.programme_tp3_biblio.modele.Client;
import com.lina.programme_tp3_biblio.service.ServiceClient;
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

    @GetMapping("/clientedit/{id}")
    public String getClientRequest(HttpServletRequest request,
                                   Model model,
                                   @PathVariable(required = false) Long clientId) {
        model.addAttribute("pageTitle", "Client");
        var client = new Client();
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null)
            client = (Client) inputFlashMap.get("client");
        model.addAttribute("client", client);
        return "clientedit";
    }

    @GetMapping("/clientcreate")
    public String getClientRequest(@ModelAttribute ClientForm clientForm,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        clientForm = new ClientForm(new Client());
        model.addAttribute("clientForm", clientForm);
        return "clientedit";
    }

    @PostMapping("/clientcreate")
    public String clientPost(@ModelAttribute ClientForm clientForm,
                             BindingResult errors,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        logger.info("client: " + clientForm);
        serviceClient.saveClient(clientForm.toClient());
        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        model.addAttribute("pageTitle", "Client");
        model.addAttribute("clientForm", clientForm);
        return "redirect:clientedit/" + clientForm.getId();
    }
}
