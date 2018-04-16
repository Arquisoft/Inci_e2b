package inci.agents.webService;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inci.agents.GetAgentInfo;
import inci.agents.dbManagement.GetAgent;
import inci.agents.util.Assert;
import inci.agents.webService.request.PeticionInfoREST;
import inci.agents.webService.responses.RespuestaInfoREST;
import inci.agents.webService.responses.errors.ErrorResponse;
import inci.entities.Agent;

@RestController
public class GetAgentInfoRESTController implements GetAgentInfo {

    @Autowired
    private GetAgent getAgent;

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = { "Accept=application/json",
	    "Accept=application/xml" }, produces = { "application/json", "text/xml" })
    public ResponseEntity<RespuestaInfoREST> getPOSTpetition(@RequestBody(required = true) PeticionInfoREST peticion) {

	Assert.isEmailEmpty(peticion.getLogin());
	Assert.isEmailValid(peticion.getLogin());
	Assert.isPasswordEmpty(peticion.getPassword());
	Assert.isKindEmpty(peticion.getKind());

	Agent agent = getAgent.getAgent(peticion.getLogin());

	Assert.isAgentNull(agent);

	Assert.isPasswordCorrect(peticion.getPassword(), agent);
	Assert.isKindCorrect(peticion.getKind(), agent);
	/*
	 * Añadimos la información al modelo, para que se muestre en la pagina html:
	 * datosAgent
	 */

	return new ResponseEntity<RespuestaInfoREST>(new RespuestaInfoREST(agent), HttpStatus.OK);
    }

    @ExceptionHandler(ErrorResponse.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleErrorResponses(ErrorResponse error) {
	return error.getMessageJSONFormat();
    }
}
