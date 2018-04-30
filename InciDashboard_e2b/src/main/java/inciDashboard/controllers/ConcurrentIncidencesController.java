package inciDashboard.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.Incidencia;
import inciDashboard.services.CommentsService;
import inciDashboard.services.CoordenadasService;
import inciDashboard.services.IncidenciasService;
import inciDashboard.services.UsersService;

@Controller
public class ConcurrentIncidencesController {
	
	@Autowired
    private UsersService usersService;

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private CoordenadasService coordenadasService;

    @Autowired
    private CommentsService commentsService;

	public List<Incidencia> incidenciasConcurrentes = new ArrayList<Incidencia>();
	
	public String saveIncidence(Incidencia incidence) {
		addIncidence(incidence);
		this.incidenciasConcurrentes.add(incidence);
		return "user/updateIncidencias";
	}
	
	private void addIncidence(Incidencia incidence) {
		usersService.addUser(incidence.getUser());
		coordenadasService.addCoordenadas(incidence.getCoordenadas());
		incidenciasService.addIndicencia(incidence);
		for(Comentario c: incidence.getComentarios())
			commentsService.addComentario(c);
	}

	public List<Incidencia> getIncidenciasConcurrentes(){
		return incidenciasConcurrentes;
	}
}
