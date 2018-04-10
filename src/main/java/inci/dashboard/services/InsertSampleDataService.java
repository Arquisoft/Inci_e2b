package inciDashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.Coordenadas;
import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;

@Service
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private CoordenadasService coordenadasService;

    @Autowired
    private CommentsService commentsService;

    @PostConstruct
    public void init() {
	User user1 = new User("Pedro", "pedro@example.com");
	user1.setPassword("1234");
	User user2 = new User("María", "maria@example.com");
	user2.setPassword("1234");

	usersService.addUser(user1);
	usersService.addUser(user2);

	Coordenadas coord1 = new Coordenadas(43.3616142, -5.8506767);
	Coordenadas coord2 = new Coordenadas(43.3579649, -5.8733862);
	coordenadasService.addCoordenadas(coord1);
	coordenadasService.addCoordenadas(coord2);
	
	Map<String, String> campos1 = new HashMap<String, String>();
	campos1.put("temp", "50");
	campos1.put("wspeed", "20");
	Map<String, String> campos2 = new HashMap<String, String>();
	campos2.put("temp", "-8");
	Map<String, String> campos3 = new HashMap<String, String>();
	campos3.put("temp", "22");
	campos3.put("wspeed", "24");
	
	List<String> tags1 = new ArrayList<String>();
	tags1.add("Temperatura Alta");
	tags1.add("Bosque");
	List<String> tags2 = new ArrayList<String>();
	tags2.add("Río");
	tags2.add("Desbordamiento");
	List<String> tags3 = new ArrayList<String>();
	tags3.add("Obstáculo");
	tags3.add("Bosque");

	Incidencia inci1 = new Incidencia("Sandra", "Incidencia en el bosque", "Temperaturas por encima de 40 grados",
		coord1, new Date(), user1, campos1, tags1);
	Incidencia inci2 = new Incidencia("Juan", "Incidencia en el río", "Río desbordándose", coord2, new Date(), user2, campos2, tags2);
	Incidencia inci3 = new Incidencia("Sandra", "Incidencia en el bosque oeste", "Tronco caído", coord1, new Date(), user1, campos3, tags3);
		
	
	incidenciasService.addIndicencia(inci1);
	incidenciasService.addIndicencia(inci2);
	incidenciasService.addIndicencia(inci3);

	Comentario c1 = new Comentario("Huele a quemado", inci1);
	Comentario c2 = new Comentario("Veo humo", inci1);
	Comentario c3 = new Comentario("Inundación", inci2);
	commentsService.addComentario(c1);
	commentsService.addComentario(c2);
	commentsService.addComentario(c3);
    }
}
