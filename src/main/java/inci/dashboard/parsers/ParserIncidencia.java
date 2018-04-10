package inciDashboard.parsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.Coordenadas;
import inciDashboard.entities.InciStatus;
import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;

@Component
public class ParserIncidencia {
	
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * 
	 * @param entrada - La incidencia en formato JSON
	 * @return incidencia - El objeto incidencia
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static Incidencia parseStringIncidencia(String entrada) throws JSONException, ParseException {		
		JSONObject obj = new JSONObject(entrada);
		JSONObject coordenadas = obj.getJSONObject("coordenadas");
		JSONObject user = obj.getJSONObject("user");
		JSONArray comentarios = obj.getJSONArray("comentarios");
		JSONArray campos = obj.getJSONArray("campos");
		JSONArray etiquetas = obj.getJSONArray("etiquetas");
		
		String nombre = obj.getString("nombre");
		String nombreUsuario = obj.getString("nombreUsuario");
		String descripcion = obj.getString("descripcion");
		Coordenadas cords = new Coordenadas(coordenadas.getDouble("Y"), coordenadas.getDouble("X"));
		
		Set<Comentario> setComentarios = new HashSet<Comentario>();
		for(int i = 0; i < comentarios.length(); i++) {
			JSONObject texto = comentarios.getJSONObject(i);
			Comentario com = new Comentario(texto.getString("texto"));
			setComentarios.add(com);
		}
		
		InciStatus estado = getEstado(obj.getString("estado"));
		Date caducidad = formatter.parse(obj.getString("fecha"));
		
		Map<String,String> mapCampos = new HashMap<String, String>();
		for(int i =0; i < campos.length(); i++) {
			JSONObject texto = campos.getJSONObject(i);
			String key = (String) texto.keys().next();
			mapCampos.put(key, texto.getString(key));
		}
		
		List<String> listaEtiquetas = new ArrayList<String>(); 
		for(int i = 0; i < etiquetas.length(); i++) {
			JSONObject texto = etiquetas.getJSONObject(i);
			listaEtiquetas.add(texto.getString("texto"));
		}
		
		User usuario = new User(user.getString("name"), user.getString("email"));
		String pass = user.getString("password");
		usuario.setPassword(pass);
		Incidencia incidencia = new Incidencia(nombreUsuario, nombre, descripcion, cords, caducidad, usuario, mapCampos, listaEtiquetas);
		incidencia.setEstado(estado);
		incidencia.setComentarios(setComentarios);
		for(Comentario c : setComentarios)
			c.setIncidencia(incidencia);
	    return incidencia;	
	}
	
	/**
	 * 
	 * @param entrada - El objeto incidencia
	 * @return salida - la incidencia en formateada como JSON 
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static String parseIncidenciaString(Incidencia entrada) throws JSONException, ParseException {	
		JSONObject obj = new JSONObject();
		obj.put("nombreUsuario", entrada.getNombreUsuario());
		obj.put("nombre", entrada.getNombre());
		obj.put("descripcion", entrada.getDescripcion());
		JSONObject coordenadas = new JSONObject();
		coordenadas.put("X", entrada.getCoordenadas().getLongitud());
		coordenadas.put("Y", entrada.getCoordenadas().getLatitud());
		obj.put("coordenadas", coordenadas);
		List<Comentario> comens = new ArrayList<Comentario>(entrada.getComentarios());
		JSONObject[] comentarios = new JSONObject[comens.size()];
		for(int i = 0; i<comens.size(); i++) {
			comentarios[i] = new JSONObject();
			comentarios[i].put("texto", comens.get(i).getTexto());
		}
		
		Map<String, String> mapCampos = entrada.getCampos();
		JSONObject[] campos = new JSONObject[mapCampos.size()];
		int i = 0;
		for(Map.Entry<String, String> campo : mapCampos.entrySet()) {
			campos[i] = new JSONObject();
			campos[i].put(campo.getKey(), campo.getValue());
			i++;
		}
		
		List<String> listaEtiquetas = new ArrayList<String>(entrada.getEtiquetas());
		JSONObject[] etiquetas = new JSONObject[listaEtiquetas.size()];
		for(i = 0; i<listaEtiquetas.size(); i++) {
			etiquetas[i] = new JSONObject();
			etiquetas[i].put("texto", listaEtiquetas.get(i));
		}
		
		obj.put("comentarios", comentarios);
		obj.put("estado", entrada.getEstado().toString());
		obj.put("fecha", formatter.format(entrada.getCaducidad().getTime()));
		obj.put("campos", campos);
		obj.put("etiquetas", etiquetas);
		
		JSONObject usuario = new JSONObject();
		usuario.put("name", entrada.getUser().getName());
		usuario.put("email", entrada.getUser().getEmail());
		usuario.put("password", entrada.getUser().getPassword());
		obj.put("user", usuario);
		String salida = obj.toString();
		return salida;
	}
	
	public static InciStatus getEstado(String est) {
		if(est.equals("ABIERTA")) 
			return InciStatus.ABIERTA;
		else if(est.equals("ENPROCESO")) 
			return InciStatus.ENPROCESO;
		else if(est.equals("CERRADA")) 
			return InciStatus.ENPROCESO;
		else if(est.equals("ANULADA")) 
			return InciStatus.ENPROCESO;
		else 
			return null;
	}
	
	public static void main(String[] args) throws JSONException, ParseException {
		String valido = "{\"nombreUsuario\":\"Raul\", "
				+ "\"nombre\":\"Incendio\", "
				+ "\"descripcion\":\"Se ha provocado un incendio en el monte Naranco\","
				+ "\"coordenadas\":{ \"X\":\"-5.866667\",\"Y\":\"43.383333\"},"
				+ "\"comentarios\":[ "
					+ "{ \"texto\":\"He llegado y he visto un humo detrás de unos arbustos, no pensaba que sería un incendio\"}, "
					+ "{ \"texto\":\"Hemos llamado a la policia hace media hora y no han llegado todavia\"} ],"
				+ "\"estado\":\"ABIERTA\", "
				+ "\"fecha\":\"11-06-2017\","
				+ "\"campos\":[ "
					+ "{ \"temp\":\"42\"}, "
					+ "{ \"velocidad viento\":\"10\"} ],"
				+ "\"etiquetas\":[ "
					+ "{ \"texto\":\"Incendio\"}, "
					+ "{ \"texto\":\"Bosque\"} ],"
				+ "\"user\":{ \"name\":\"Raul\",\"email\":\"raul@gmail.com\",\"password\":\"nfdas923nljd\"}}";
		Incidencia inci = parseStringIncidencia(valido);
		System.out.println(inci.toString());
		System.out.println(parseIncidenciaString(inci));
	}

}
