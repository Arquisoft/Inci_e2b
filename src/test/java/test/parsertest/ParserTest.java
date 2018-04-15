package test.parsertest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.junit.After;
import org.junit.Test;

import com.lowagie.text.DocumentException;

import inci.entities.Usuario;
import inci.loader.parser.RList;
import inci.loader.parser.ReaderSingleton;
import inci.loader.persistence.UserFinder;
import inci.loader.persistence.util.Jpa;

public class ParserTest {

	@Test
	public void testLoadExcelExito() throws FileNotFoundException, DocumentException {
		RList ex = new RList();
		ex.loadExcel("src/test/resources/test.xlsx");

		assertEquals(ex.getAllUsers().size(), 6);

		List<XSSFCell> list1 = ex.getAllUsers().get(3);
		List<XSSFCell> list2 = ex.getAllUsers().get(4);
		List<XSSFCell> list3 = ex.getAllUsers().get(5);
		StringBuilder st = new StringBuilder();

		for (int i = 0; i < list1.size(); i++) {
			if ((i != 3 && list1.size()<5) || (i!=4 && list1.size()==5))
				st.append(list1.get(i).toString() + " ");
		}

		assertEquals(st.toString(), "Sensor humedad 22º 17' N, 8º 75' S organizacion2@example.com 12348952O ");

		st = new StringBuilder();

		for (int i = 0; i < list2.size(); i++) {
			if ((i != 3 && list2.size()<5) || (i!=4 && list2.size()==5))
				st.append(list2.get(i).toString() + " ");
		}

		assertEquals(st.toString(), "Ana García Pérez anagp@example.com 09940449X ");

		st = new StringBuilder();

		for (int i = 0; i < list3.size(); i++) {
			if ((i != 3 && list3.size()<5) || (i!=4 && list3.size()==5))
				st.append(list3.get(i).toString() + " ");
		}

		assertEquals(st.toString(), "Bomberos de Asturias bomberosast@example.com 23548912Z ");
	}

	@Test(expected = FileNotFoundException.class)
	public void testLoadExcelFicheroNoEncontrado() throws FileNotFoundException, DocumentException {
		RList ex = new RList();
		ex.loadExcel("src/test/resources/fallo.xlsx");

		assertEquals(ex.getAllUsers().size(), 3);

		List<XSSFCell> list1 = ex.getAllUsers().get(0);
		List<XSSFCell> list2 = ex.getAllUsers().get(1);
		List<XSSFCell> list3 = ex.getAllUsers().get(2);
		StringBuilder st = new StringBuilder();

		for (int i = 0; i < list1.size(); i++) {
			st.append(list1.get(i).toString() + " ");
		}

		assertEquals(st.toString(), "Juan Torres Pardo juan@example.com " + list1.get(3)
				+ " C/ Federico García Lorca 2 Español 90500084Y ");

		st = new StringBuilder();

		for (int i = 0; i < list2.size(); i++) {
			st.append(list2.get(i).toString() + " ");
		}

		assertEquals(st.toString(),
				"Luis López Fernando luis@example.com " + list2.get(3) + " C/ Real Oviedo 2 Español 19160962F ");

		st = new StringBuilder();

		for (int i = 0; i < list3.size(); i++) {
			st.append(list3.get(i).toString() + " ");
		}

		assertEquals(st.toString(),
				"Ana Torres Pardo ana@example.com " + list3.get(3) + " Av. De la Constitución 8 Español 09940449X ");
	}

	@Test(expected = IOException.class)
	public void testLoadExcelErrorExcel() throws IOException, DocumentException {
		RList ex = new RList();
		ex.loadExcel("src/test/resources/vaciop.xlsx");

		assertEquals(ex.getAllUsers().size(), 0);

		List<XSSFCell> list1 = ex.getAllUsers().get(0);
		List<XSSFCell> list2 = ex.getAllUsers().get(1);
		List<XSSFCell> list3 = ex.getAllUsers().get(2);
		StringBuilder st = new StringBuilder();

		for (int i = 0; i < list1.size(); i++) {
			st.append(list1.get(i).toString() + " ");
		}

		assertEquals(st.toString(),
				"Juan Torres Pardo juan@example.com 10-oct-1985 C/ Federico García Lorca 2 Español 90500084Y ");

		st = new StringBuilder();

		for (int i = 0; i < list2.size(); i++) {
			st.append(list2.get(i).toString() + " ");
		}

		assertEquals(st.toString(),
				"Luis López Fernando luis@example.com 02-mar-1970 C/ Real Oviedo 2 Español 19160962F ");

		st = new StringBuilder();

		for (int i = 0; i < list3.size(); i++) {
			st.append(list3.get(i).toString() + " ");
		}

		assertEquals(st.toString(),
				"Ana Torres Pardo ana@example.com 01-ene-1960 Av. De la Constitución 8 Español 09940449X ");
	}

	@Test
	public void testReaderSingleton() throws DocumentException {
		ReaderSingleton rS = ReaderSingleton.getInstance();
		rS.loadFile("cadenaIncorrecta","cadenaIncorrecta");
		rS.loadFile("test.xlsx", "users.csv");
		ReaderSingleton rS1 = ReaderSingleton.getInstance();
		rS.loadFile("cadenaIncorrecta","cadenaIncorrecta");
		rS.loadFile("test.xlsx", "users.csv");
		assertEquals(rS, rS1);
	}

	@After
	public void deleting() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Usuario> aBorrar = UserFinder.findByDNI("09940449X");
		if (!aBorrar.isEmpty())
			Jpa.getManager().remove(aBorrar.get(0));

		aBorrar = UserFinder.findByDNI("19160962F");
		if (!aBorrar.isEmpty())
			Jpa.getManager().remove(aBorrar.get(0));

		aBorrar = UserFinder.findByDNI("90500084Y");
		if (!aBorrar.isEmpty())
			Jpa.getManager().remove(aBorrar.get(0));

		trx.commit();
		mapper.close();

	}
}

