package inci.manager.incidences.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import inci.entities.Incidence;
import inci.manager.incidences.service.IncidenceService;

@RestController
public class IncidenceRestController {

    @Autowired
    public IncidenceService incService;

    @RequestMapping(value = "/incidence/send", method = RequestMethod.POST, headers = { "Accept=application/json",
	    "Accept=application/xml" })
    public String sendIncidence(@RequestBody Map<String, Object> payload) {
	String usuario = (String) payload.get("usuario");
	String pass = (String) payload.get("password");
	System.out.println(payload);
	// String kind = incidence.get;

	try {
	    HttpResponse<JsonNode> response = incService.checkUser(usuario, pass, "1");

	    if (response.getStatus() != 200) {
		return response.getBody().toString();
	    } else if (response.getStatus() == 200) {
		incService.sendKaffka(payload);
		return response.getBody().toString();
	    }
	    return "{\"error\": \"Could not find response\"}";
	} catch (UnirestException e) {
	    e.printStackTrace();
	}

	return "{\"error\": \"Could not connect\"}";
    }

    @ModelAttribute("incidence")
    public Incidence getIncidence() {
	return new Incidence();
    }
}
