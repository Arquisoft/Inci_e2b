package asw.agents.webService.responses.errors;

public class RequiredKindErrorResponse extends ErrorResponse {

	private static final long serialVersionUID = 242754484115507912L;

	@Override
	public String getMessageJSONFormat() {
		return "{\"reason\": \"User Kind is required\"}";
	}

	@Override
	public String getMessageStringFormat() {
		return "User Kind is required";
	}

}
