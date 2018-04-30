package inciDashboard.controllers;

import inciDashboard.entities.Incidencia;
import inciDashboard.services.IncidenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class IncidenciasController {

    @Autowired
    private IncidenciasService incidenciasService;

    @RequestMapping(value = "/incidence/{id}", method = RequestMethod.GET)
    public String getIncidenceDangerousValues(Model model, @PathVariable Long id) {
        model.addAttribute("fields", incidenciasService.getDangerousValues(id));
        return "incidences/dangerousFields";
    }

}
