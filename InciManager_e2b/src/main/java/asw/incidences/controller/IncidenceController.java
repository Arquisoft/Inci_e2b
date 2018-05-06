package asw.incidences.controller;

import asw.dbManagement.model.Incidencia;
import asw.dbManagement.repository.IncidenceRepository;
import asw.incidences.service.IncidenceService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IncidenceController  implements ErrorController {
	
	@Autowired
	public IncidenceService incService;

	@Autowired
    public IncidenceRepository repo;

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
	 @RequestParam(required = false) String etiquetas,@RequestParam Map<String,String> requestParams){
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
                Map<String,String> campos = new HashMap<>();
                for(String s : requestParams.keySet()){
                    if(s.contains("campo-")){
                        campos.put(s.replace("campo-",""),requestParams.get(s));
                    }
                }
				map.put("campos", campos);
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

	@RequestMapping(method = RequestMethod.POST,value = "/check")
	public String check(Model m, @RequestParam String usuario,
						@RequestParam String password){
        m.addAttribute("incidenciasList",new ArrayList<Incidencia>());
		try {
			HttpResponse<JsonNode> res = incService.checkUser(usuario,password, String.valueOf(1));
			if(res.getStatus() == 200){
                List<Incidencia> r = repo.findByNombreUsuario(usuario);
                m.addAttribute("incidenciasList", r);
                return "check";
			}else{
				m.addAttribute("succsed", false);
				m.addAttribute("error", res.getBody().toString());
			}
		} catch (UnirestException  e) {
			e.printStackTrace();
			m.addAttribute("succsed", false);
		}
		m.addAttribute("second",true);
        return "input";
	}



    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletResponse httpServletResponse) {
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
