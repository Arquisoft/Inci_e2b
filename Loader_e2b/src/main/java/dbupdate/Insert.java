package dbupdate;

import java.util.List;

import model.Agent;
import model.Usuario;

/**
 * 
 * @author Ignacio Escribano Burgos
 * Gestiona la conexión con la base de datos
 *
 */
public interface Insert {
	List<Agent> findByEmail(String email);

	Usuario save(Usuario user);

	List<Agent> findByDNI(String dni);
}
