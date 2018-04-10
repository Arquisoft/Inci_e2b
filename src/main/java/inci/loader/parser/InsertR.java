package inci.loader.parser;

import java.util.List;

import inci.loader.dbupdate.Insert;
import inci.loader.dbupdate.InsertP;
import inci.loader.model.Usuario;
import inci.loader.persistence.UserFinder;

public class InsertR implements Insert {

	@Override
	public Usuario save(Usuario user){
		return new InsertP().save(user);
	}

	@Override
	public List<Usuario> findByDNI(String dni) {
		return UserFinder.findByDNI(dni);
	}

	@Override
	public List<Usuario> findByEmail(String email) {
		return UserFinder.findByEmail(email);
	}
}
