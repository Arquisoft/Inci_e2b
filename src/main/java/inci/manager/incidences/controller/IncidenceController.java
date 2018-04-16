package inci.manager.incidences.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import inci.entities.Incidence;
import inci.manager.incidences.service.IncidenceService;

@Controller
public class IncidenceController {

    @Autowired
    public IncidenceService incService;

    @RequestMapping("/")
    public String send() {
	return "input";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/input")
    public String sended(Model m, @ModelAttribute("incidence") Incidence incidence) {
	try {
	    HttpResponse<JsonNode> res = incService.checkUser(incidence.getUsuario(), incidence.getPassword(),
		    String.valueOf(1));
	    if (res.getStatus() == 200) {
		m.addAttribute("succsed", true);
		incService.sendKaffka(incidence);

	    } else {
		m.addAttribute("succsed", false);
		m.addAttribute("error", res.getBody().toString());
	    }
	} catch (UnirestException e) {
	    e.printStackTrace();
	    m.addAttribute("succsed", false);
	}
	return "input";
    }

}
