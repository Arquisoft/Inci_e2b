package inciDashboard.kafka.listeners;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import inciDashboard.controllers.MainController;
import inciDashboard.controllers.UsersController;
import inciDashboard.entities.Incidencia;
import inciDashboard.kafka.ConcurrentIncidences;
import inciDashboard.kafka.producers.util.Topics;
import inciDashboard.parsers.ParserIncidencia;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class IncidenceListener {
	
	private static final Logger logger = Logger.getLogger(IncidenceListener.class);

    @Autowired
    private ConcurrentIncidences incidenciasConcurrentes;
    
    @Autowired
    private ParserIncidencia parserJSON;

    @KafkaListener(topics = Topics.NEW_INCIDENCE)
    public void listen(String data) throws JSONException, ParseException {
    	logger.info("New message received: \"" + data + "\"");
    	Incidencia incidencia;
    	incidencia = parserJSON.parseStringIncidencia(data);
    	
		if(incidencia!=null)
			incidenciasConcurrentes.saveIncidence(incidencia);
	}

}
