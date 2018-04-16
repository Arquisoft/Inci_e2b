package inci.dashboard.kafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import inci.dashboard.services.CoordenadasService;
import inci.dashboard.services.IncidenciasService;
import inci.entities.Incidence;

@Component
public class ConcurrentIncidences {

    @Autowired
    private IncidenciasService servicioIncidencias;

    @Autowired
    private CoordenadasService servicioCoordenadas;

    public List<Incidence> incidenciasConcurrentes = new ArrayList<Incidence>();

    public void saveIncidence(Incidence incidence) {
	servicioCoordenadas.addCoordenadas(incidence.getCoordenadas());
	servicioIncidencias.addIndicencia(incidence);
	this.incidenciasConcurrentes.add(incidence);
    }

    public List<Incidence> getIncidenciasConcurrentes() {
	return incidenciasConcurrentes;
    }
}
