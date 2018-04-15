package inci.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ciudadanos")
@DiscriminatorValue("Ciudadanos")
public class Entidad extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Entidad() {
	}

	public Entidad(String nombre, String email, String code) {
		setName(nombre);
		setEmail(email);
		setCodigo(code);
	}

}
