package model;

import java.io.Serializable;

public class Entidad extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	Entidad() {
	}

	public Entidad(String nombre, String email, String code) {
		setNombre(nombre);
		setEmail(email);
		setCodigo(code);
	}

}
