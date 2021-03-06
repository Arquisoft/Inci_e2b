package asw.incidences.service;

import asw.dbManagement.model.Coordenadas;
import asw.dbManagement.model.InciStatus;
import asw.dbManagement.model.Incidencia;
import asw.dbManagement.model.User;
import asw.dbManagement.model.parsers.ParserIncidencia;
import asw.kaffkaManagement.KafkaProducer;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class IncidenceService {

	@Autowired
	private KafkaProducer kafkaProducer;

	public HttpResponse<JsonNode> checkUser(String user, String pass, String kind) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post("http://18.236.140.130:8080/user")
				.header("content-type", "application/json").header("cache-control", "no-cache")
				.body("{\n\"login\":\"" + user + "\",\n\"password\":\"" + pass + "\",\n\"kind\":\"" + kind + "\"\n}\n")
				.asJson();
		return response;
	}

	/*public void sendKaffka(Incidencia incidence) {
		JSONObject json = new JSONObject();
		json.put("usuario", incidence.getUsuario());
		json.put("password", incidence.getPassword());
		json.put("nombre", incidence.getNombre());
		json.put("descripcion", incidence.getDescripcion());
		json.put("localizacion", incidence.getLocalizacion());
		if(incidence.getEtiquetas() != null && incidence.getEtiquetas().length() > 0){
			json.put("etiquetas", incidence.getEtiquetas().split(","));
		}
		json.put("extra", incidence.getExtra());
		kafkaProducer.send("incidencia", json.toString());
	}*/
	
	public void sendKaffka(Map<String, Object> payload) {
		Incidencia inc = new Incidencia();
        inc.setNombreUsuario((String) payload.get("usuario"));
        inc.setNombre((String) payload.get("nombre"));
        inc.setCampos((HashMap) payload.get("campos"));
        inc.setComentarios(new HashSet<>());


        if( payload.get("etiquetas") == null){
            inc.setEtiquetas(new ArrayList<>());
        }else if( payload.get("etiquetas") instanceof  String ){
            String[] et = ((String) payload.get("etiquetas")).split(",");
            List<String> cs = new ArrayList<>();
            for(String e :et){
                cs.add(e);
            }
            inc.setEtiquetas(cs);
        }else if(payload.get("etiquetas") instanceof  List){
            inc.setEtiquetas((List<String>) payload.get("etiquetas"));
        }
        inc.setCoordenadas(new Coordenadas(Double.valueOf((String) payload.get("latitud")),Double.valueOf((String) payload.get("longitud"))));
        inc.setDescripcion((String) payload.get("descripcion"));
        inc.setEstado(InciStatus.ABIERTA);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 3);
        dt = c.getTime();
        inc.setCaducidad(dt);
        User u = new User();
        u.setEmail(inc.getNombreUsuario());
        u.setName(inc.getNombreUsuario());
        u.setPassword("");
        inc.setUser(u);

        try {
            kafkaProducer.send("rjkjinkm-incidencia", ParserIncidencia.parseIncidenciaString(inc));
        } catch (ParseException e) {
            System.out.println("No se logro parsear la incidencia");
            e.printStackTrace();
        }
    }

}
