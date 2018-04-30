package asw.agents.webService.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import asw.dbManagement.model.Agent;

@XmlRootElement(name = "agent")
public class RespuestaInfoREST {	
	
	private String name;
	private String location;
	private String email;
	private String id;
	private String kind;
	private int kindCode;
	
	
	/*
	 * "name": Nombre,
 "location": Coordenadas (opcional),
 "email": Email
 "id": identificador,
 "kind": tipo de usuario,
 "kindCode": código numérico del tipo de usuario
	 */
	
	public RespuestaInfoREST() {}
	
	public RespuestaInfoREST(Agent agent){
		setName(agent.getNombre());
		setEmail(agent.getEmail());
		setId(agent.getIdent());
		setLocation(agent.getLocalizacion());
		setKind(agent.getKind());
		setKindCode(agent.getKindCode());
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	@XmlElement
	public void setLocation(String location) {
		this.location = location;
	}

	public String getKind() {
		return kind;
	}

	@XmlElement
	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getKindCode() {
		return kindCode;
	}

	@XmlElement
	public void setKindCode(Integer kindCode) {
		if(kindCode == null){
			return;
		}
		this.kindCode = kindCode;
	}

	

}
