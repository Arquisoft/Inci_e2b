package inci.loader.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ciudadanos")
@DiscriminatorValue("Ciudadanos")
public class Entidad extends Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Entidad() {
	}

	public Entidad(String nombre, String email, String code) {
		setNombre(nombre);
		setEmail(email);
		setCodigo(code);
	}

}
