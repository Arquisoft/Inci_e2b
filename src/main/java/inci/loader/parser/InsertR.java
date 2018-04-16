package inci.loader.parser;

import java.util.List;

import inci.entities.User;
import inci.loader.dbupdate.Insert;
import inci.loader.dbupdate.InsertP;
import inci.loader.persistence.UserFinder;

public class InsertR implements Insert {

    @Override
    public User save(User user) {
	return new InsertP().save(user);
    }

    @Override
    public List<User> findByDNI(String dni) {
	return UserFinder.findByDNI(dni);
    }

    @Override
    public List<User> findByEmail(String email) {
	return UserFinder.findByEmail(email);
    }
}
