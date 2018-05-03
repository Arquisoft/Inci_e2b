package asw.incidences.controller;

import asw.incidences.service.IncidenceService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IncidenceController {
	
	@Autowired
	public IncidenceService incService;
	

	@RequestMapping("/")
	public String send(){
		return "input";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/input")
	public String sended(Model m,
	 @RequestParam String usuario,
	 @RequestParam String password,
	 @RequestParam String nombre,
	 @RequestParam String descripcion,
	 @RequestParam String latitud,
	 @RequestParam String longitud,
	 @RequestParam(required = false) String etiquetas){
		try {
			HttpResponse<JsonNode> res = incService.checkUser(usuario,password, String.valueOf(1));
			if(res.getStatus() == 200){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("usuario",usuario);
				map.put("password",password);
				map.put("nombre",nombre);
				map.put("descripcion",descripcion);
				map.put("latitud",latitud);
				map.put("longitud",longitud);
				if(etiquetas == null){
				    etiquetas = "";
                }
				map.put("etiquetas",etiquetas);
				m.addAttribute("succsed", true);
				incService.sendKaffka(map);
				
			}else{
				m.addAttribute("succsed", false);
				m.addAttribute("error", res.getBody().toString());
			}
		} catch (UnirestException  e) {
			e.printStackTrace();
			m.addAttribute("succsed", false);
		}
		return "input";
	}

	@RequestMapping(method = RequestMethod.POST,value = "check")
	public String check(Model m, @RequestParam String usuario,
						@RequestParam String password){

		try {
			HttpResponse<JsonNode> res = incService.checkUser(usuario,password, String.valueOf(1));
			if(res.getStatus() == 200){

			}else{
				m.addAttribute("succsed", false);
				m.addAttribute("error", res.getBody().toString());
			}
		} catch (UnirestException  e) {
			e.printStackTrace();
			m.addAttribute("succsed", false);
		}
		return "check";
	}
	
	
}
