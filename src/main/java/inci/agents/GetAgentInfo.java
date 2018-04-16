package inci.agents;

import org.springframework.http.ResponseEntity;

import inci.agents.webService.request.PeticionInfoREST;
import inci.agents.webService.responses.RespuestaInfoREST;

public interface GetAgentInfo {

    public ResponseEntity<RespuestaInfoREST> getPOSTpetition(PeticionInfoREST peticion);

}
