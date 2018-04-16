package inci.dashboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inci.entities.InciStatus;
import inci.entities.Incidence;
import inci.entities.User;
import inci.repositories.IncidenciasRepository;
import inciDashboard.entities.Incidencia;

@Service
public class IncidenciasService {
    @Autowired
    private IncidenciasRepository incidenciasRepository;

    public List<Incidence> getIncidencias() {
	List<Incidence> incidencias = new ArrayList<Incidence>();
	incidenciasRepository.findAll().forEach(incidencias::add);
	return incidencias;
    }

    public Incidence getIncidencia(Long id) {
	return incidenciasRepository.findOne(id);
    }

    public void addIndicencia(Incidence incidencia) {
	incidenciasRepository.save(incidencia);
    }

    public void deleteIncidencia(Long id) {
	incidenciasRepository.delete(id);
    }

    public List<Incidence> getIncidenciasByUser(User user) {
	return incidenciasRepository.findAllByUser(user);
    }

    public void updateStatus(Enum<InciStatus> estado, Long idIncidencia) {
	incidenciasRepository.setStatus(estado, idIncidencia);
    }

    public Map<String, String> getDangerousValues(Long id) {
	return getDangerousValuesAux(incidenciasRepository.getDangerousValues(id).getCampos());
    }

    private Map<String, String> getDangerousValuesAux(Map<String, String> fields) {
	for (Map.Entry<String, String> entry : fields.entrySet()) {
	    if (entry.getKey().equals("temp")) {
		int temp = Integer.valueOf(entry.getValue());
		{
		    if (temp <= 40 || temp >= 1)
			fields.remove(entry);
		}
	    } else if (entry.getKey().equals("wspeed")) {
		int speed = Integer.valueOf(entry.getValue());
		{
		    if (speed <= 60 || speed >= 5)
			fields.remove(entry);
		}
	    }
	}
	return fields;
    }

}
