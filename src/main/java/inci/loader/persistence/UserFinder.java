package inci.loader.persistence;

import java.util.List;

import inci.loader.model.Usuario;
import inci.loader.persistence.util.Jpa;

public class UserFinder {

	public static List<Usuario> findByDNI(String dni) {
		return Jpa.getManager().createNamedQuery("User.findByDni", Usuario.class).
				setParameter(1, dni).getResultList();
	}

	public static List<Usuario> findByEmail(String email) {
		return Jpa.getManager().createNamedQuery("User.findByEmail", Usuario.class).
				setParameter(1, email).getResultList();
	}
}
