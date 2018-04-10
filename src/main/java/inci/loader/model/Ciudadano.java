package inci.loader.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ciudadanos")
@DiscriminatorValue("Ciudadanos")
public class Ciudadano extends Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String username;
	private String password;

	Ciudadano() {
	}

	public Ciudadano(String nombre, String email, String DNI) {
		setNombre(nombre);
		setEmail(email);
		setCodigo(DNI);
		setUsername(email);
		generarPassword();
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private void setUsername(String username) {
		this.username = username;
	}


	private void generarPassword() {
		StringBuffer pass = new StringBuffer();
		int low = 65;
		int top = 90;
		for (int i = 0; i < 9; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (top - low) + low);
			pass.append((char) numAleatorio);
		}
		for (int i = 0; i < 3; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (9 - 0) + 0);
			pass.append(numAleatorio);
		}
		setPassword(pass.toString());
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
