package inciDashboard.kafka.listeners;

import inciDashboard.controllers.UsersController;
import inciDashboard.entities.Incidencia;
import inciDashboard.kafka.Topics;
import inciDashboard.parsers.ParserIncidencia;
import inciDashboard.services.IncidenciasService;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.ManagedBean;
import java.text.ParseException;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class IncidenceListener {

    private static final Logger logger = Logger.getLogger(IncidenceListener.class);

    @Autowired
    private IncidenciasService incidencias;

    @Autowired
    private ParserIncidencia parserJSON;

    @Autowired
    private UsersController iCon;

    @KafkaListener(topics = Topics.NEW_INCIDENCE)
    public void listen(String data) throws JSONException, ParseException {
        logger.info("New message received: \"" + data + "\"");

        Incidencia incidencia = parserJSON.parseStringIncidencia(data);


        if (incidencia != null) {
            incidencia.setUser(null);
            incidencias.addIndicenciaFull(incidencia);

            Incidencia is = incidencias.getIncidenciasByUser(null).stream().filter(f -> f.getNombreUsuario().equals(incidencia.getNombreUsuario()) &&
                    f.getDescripcion().equals(incidencia.getDescripcion()) && f.getNombre().equals(incidencia.getNombre())).findFirst().orElse(null);
            iCon.sendData(SseEmitter.event().name(("aviso")).data( parserJSON.parseIncidenciaString(is) ));
        }
    }

}
