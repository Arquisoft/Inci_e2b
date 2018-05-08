package asw.incidences.controller;

import asw.incidences.service.IncidenceService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
		//String kind = incidence.get;

		try {
			HttpResponse<JsonNode> response = incService.checkUser(usuario, pass, "1");
			
			if(response.getStatus() != 200){
				return response.getBody().toString();
			}else if(response.getStatus() == 200){
				Map<String,String> campos = new HashMap<>();
				for(String s : payload.keySet()){
					if(s.contains("campo-")){
						payload.put(s.replace("campo-",""),payload.get(s));
					}
				}
				incService.sendKaffka(payload);
				return response.getBody().toString();
			}
			return "{\"error\": \"Could not find response\"}";
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		return "{\"error\": \"Could not connect\"}";
	}


}
