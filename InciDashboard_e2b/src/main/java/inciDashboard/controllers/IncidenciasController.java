package inciDashboard.controllers;

import inciDashboard.services.IncidenciasService;
import inciDashboard.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class IncidenciasController {

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/incidence/{id}", method = RequestMethod.GET)
    public String getIncidenceDangerousValues(Model model, @PathVariable Long id) {
        model.addAttribute("fields", incidenciasService.getDangerousValues(id));
        return "incidences/dangerousFields";
    }

    @RequestMapping(value = "/incidence/user/{id}", method = RequestMethod.POST)
    public String getIncidenceDangerousValues(Model model, @PathVariable Long id, Principal pr) {
        incidenciasService.updateUser(usersService.getUserByEmail(pr.getName()),id);
        return "user/listIncidencias";
    }

}
