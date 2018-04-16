package inciDashboard.kafka.listeners;

import java.text.ParseException;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import inci.dashboard.kafka.ConcurrentIncidences;
import inci.dashboard.kafka.producers.util.Topics;
import inci.dashboard.parsers.ParserIncidencia;
import inci.entities.Incidence;

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
	Incidence incidencia;
	incidencia = parserJSON.parseStringIncidencia(data);

	if (incidencia != null)
	    incidenciasConcurrentes.saveIncidence(incidencia);
    }

}
