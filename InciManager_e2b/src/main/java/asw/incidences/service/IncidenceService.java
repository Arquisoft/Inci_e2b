package asw.incidences.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import asw.dbManagement.model.Incidence;
import asw.kaffkaManagement.KafkaProducer;

@Service
public class IncidenceService {

	@Autowired
	private KafkaProducer kafkaProducer;

	public HttpResponse<JsonNode> checkUser(String user, String pass, String kind) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/user")
				.header("content-type", "application/json").header("cache-control", "no-cache")
				.body("{\n\"login\":\"" + user + "\",\n\"password\":\"" + pass + "\",\n\"kind\":\"" + kind + "\"\n}\n")
				.asJson();
		return response;
	}

	public void sendKaffka(Incidence incidence) {
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
	}
	
	public void sendKaffka(Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		for(String k : payload.keySet()){
			json.put(k, payload.get(json));
		}
		kafkaProducer.send("incidencia", json.toString());
	}

}
