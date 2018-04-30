package model;

import java.io.Serializable;


public class Ciudadano extends Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String username;
	

	Ciudadano() {
	}

	public Ciudadano(String nombre, String email, String DNI) {
		setNombre(nombre);
		setEmail(email);
		setCodigo(DNI);
		setUsername(email);
	}

	

	private void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
