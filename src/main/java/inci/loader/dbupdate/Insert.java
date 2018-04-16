package inci.loader.dbupdate;

import java.util.List;

import inci.entities.User;

/**
 * 
 * @author Ignacio Escribano Burgos Gestiona la conexión con la base de datos
 *
 */
public interface Insert {
    List<User> findByEmail(String email);

    User save(User user);

    List<User> findByDNI(String dni);
}
