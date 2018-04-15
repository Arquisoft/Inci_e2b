package test.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import inci.entities.Ciudadano;
import inci.entities.Entidad;
import inci.entities.Location;
import inci.entities.Sensor;
import inci.entities.Usuario;
import inci.loader.model.*;

public class UserTest {

	private Usuario user1 = null;
	private Usuario user2 = null;
	private Usuario user3 = null;
	private Usuario user4 = null;
	private Usuario user5 = null;
	private Usuario user6 = null;

	@Before
	public void setUp() {
		user1 = new Ciudadano("Juan Torres Pardo", "juantp@example.com", "90500084Y");
		user2 = new Sensor("Sensor Temperatura", new Location("43º 22' N", "5º 50' O"), "organizacion1@example.com", "78964566P");
		user3 = new Ciudadano("Luis López Fernandez", "luisgf@example.com", "19160962F");
		user4 = new Sensor("Sensor humedad", new Location("22º 17' N", "8º 75' S"), "organizacion2@example.com", "12348952O");
		user5 = new Ciudadano("Ana García Pérez", "anagp@example.com", "09940449X");
		user6 = new Entidad("Bomberos de Asturias", "bomberosast@example.com", "23548912Z");
	}

	@Test
	public void testAll() {
		assertEquals("Juan Torres Pardo", user1.getNombre());
		assertEquals("juantp@example.com", user1.getEmail());
		assertEquals("90500084Y", user1.getCodigo());
		
		assertEquals("Sensor Temperatura", user2.getNombre());
		assertEquals("organizacion1@example.com", user2.getEmail());
		assertEquals("78964566P", user2.getCodigo());

		assertEquals("Luis López Fernandez", user3.getNombre());
		assertEquals("luisgf@example.com", user3.getEmail());
		assertEquals("19160962F", user3.getCodigo());

		assertEquals("Sensor humedad", user4.getNombre());
		assertEquals("organizacion2@example.com", user4.getEmail());
		assertEquals("12348952O", user4.getCodigo());
	
		assertEquals("Ana García Pérez", user5.getNombre());
		assertEquals("anagp@example.com", user5.getEmail());
		assertEquals("09940449X", user5.getCodigo());

		assertEquals("Bomberos de Asturias", user6.getNombre());
		assertEquals("bomberosast@example.com", user6.getEmail());
		assertEquals("23548912Z", user6.getCodigo());
	}

	@Test
	public void testType() {
		assertTrue(user1 instanceof Ciudadano);
		assertFalse(user1 instanceof Sensor);
		assertFalse(user1 instanceof Entidad);

		assertTrue(user2 instanceof Sensor);
		assertFalse(user2 instanceof Ciudadano);
		assertFalse(user2 instanceof Entidad);

		assertTrue(user3 instanceof Ciudadano);
		assertFalse(user3 instanceof Sensor);
		assertFalse(user3 instanceof Entidad);

		assertTrue(user4 instanceof Sensor);
		assertFalse(user4 instanceof Ciudadano);
		assertFalse(user4 instanceof Entidad);

		assertTrue(user5 instanceof Ciudadano);
		assertFalse(user5 instanceof Sensor);
		assertFalse(user5 instanceof Entidad);

		assertTrue(user6 instanceof Entidad);
		assertFalse(user6 instanceof Sensor);
		assertFalse(user6 instanceof Ciudadano);
	}

}
