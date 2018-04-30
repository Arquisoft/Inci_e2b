package parser;

import java.util.List;

import dbupdate.Insert;
import dbupdate.InsertP;
import model.Agent;
import model.Usuario;
import persistence.UserFinder;

public class InsertR implements Insert {

	@Override
	public Usuario save(Usuario user){
		return new InsertP().save(user);
	}

	@Override
	public List<Agent> findByDNI(String dni) {
		return UserFinder.findByIdent(dni);
	}

	@Override
	public List<Agent> findByEmail(String email) {
		return UserFinder.findByEmail(email);
	}
}
