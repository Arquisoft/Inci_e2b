package dbupdate;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import model.Agent;
import model.Ciudadano;
import model.Sensor;
import model.Usuario;
import parser.cartas.LetterGenerator;
import persistence.UserFinder;
import persistence.util.Jpa;
import reportwriter.ReportWriter;

public class InsertP implements Insert {

	@Override
	public Usuario save(Usuario user)  {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		try {
			if (!UserFinder.findByIdent(user.getCodigo()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"El usuario con el dni " + user.getCodigo() + " ya existe en la base de datos");
				trx.rollback();
			} else if (!UserFinder.findByEmail(user.getEmail()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"Ya existe un usuario con el email " + user.getEmail() + " en la base de datos");
				trx.rollback();
			} else {
				Agent a = new Agent(user.getNombre(),  user.getPassword(), user.getEmail(),user.getLocation() == null ? "" : user.getLocation().getLongitude() + " " +
						user.getLocation().getLatitude(),user.getCodigo(),
						user instanceof Ciudadano ? 1 : (user instanceof Sensor ? 3 : 2));
				Jpa.getManager().persist(a);
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
	public List<Agent> findByDNI(String dni) {
		return UserFinder.findByIdent(dni);
	}

	@Override
	public List<Agent> findByEmail(String email) {
		return UserFinder.findByEmail(email);
	}
}
