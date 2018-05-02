package inciDashboard.kafka.listeners;

import inciDashboard.controllers.ConcurrentIncidencesController;
import inciDashboard.entities.Incidencia;
import inciDashboard.kafka.producers.util.Topics;
import inciDashboard.parsers.ParserIncidencia;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.ManagedBean;
import java.text.ParseException;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class IncidenceListener {
	
	private static final Logger logger = Logger.getLogger(IncidenceListener.class);

    @Autowired
    private ConcurrentIncidencesController incidenciasConcurrentes;
    
    @Autowired
    private ParserIncidencia parserJSON;

    @KafkaListener(topics = Topics.NEW_INCIDENCE)
    public void listen(String data) throws JSONException, ParseException {
    	logger.info("New message received: \"" + data + "\"");

    	Incidencia incidencia;
    	incidencia = parserJSON.parseStringIncidencia(data);

    	System.out.println(incidencia.toString());
		if(incidencia!=null)
			incidenciasConcurrentes.saveIncidence(incidencia);
	}

}
