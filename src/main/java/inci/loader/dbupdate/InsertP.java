package inci.loader.dbupdate;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import inci.entities.Usuario;
import inci.loader.parser.cartas.LetterGenerator;
import inci.loader.persistence.UserFinder;
import inci.loader.persistence.util.Jpa;
import inci.loader.reportwriter.ReportWriter;

public class InsertP implements Insert {

	@Override
	public Usuario save(Usuario user)  {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		try {
			if (!UserFinder.findByDNI(user.getCodigo()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"El usuario con el dni " + user.getCodigo() + " ya existe en la base de datos");
				trx.rollback();
			} else if (!UserFinder.findByEmail(user.getEmail()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"Ya existe un usuario con el email " + user.getEmail() + " en la base de datos");
				trx.rollback();
			} else {
				Jpa.getManager().persist(user);
				trx.commit();
				
				LetterGenerator.generateTxtLetter(user);
				LetterGenerator.generatePdfLetter(user);
				LetterGenerator.generateWordLetter(user);
				
			}
		} catch (PersistenceException ex) {
			ReportWriter.getInstance().getWriteReport().log(Level.WARNING, "Error de la BBDD");
			if (trx.isActive())
				trx.rollback();
		} finally {
			if (mapper.isOpen())
				mapper.close();
		}
		return user;
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
