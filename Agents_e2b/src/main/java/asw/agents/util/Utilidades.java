package asw.agents.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;


public  class Utilidades {
	///////////////////////////////////////////////////////////////
	/// //
	/// Clase creada para realizar funciones que no //
	/// tengan que ver con la l�gica de los controladores //
	/// //
	//////////////////////////////////////////////////////////

//metido en assert
//
//	public static boolean validarCorreo(String mail) {
//		String[] mailSplit = mail.split("@");
//		if (mailSplit.length != 2 || mailSplit[0].length() == 0) {
//			return false;
//		}
//		mailSplit = mail.split(".");
//		if (mailSplit.length != 2 || mailSplit[0].length() == 0 || mailSplit[1].length() == 0) {
//			return false;
//		}
//
//		return true;
//	}

	public static int getEdad(Date fechaNacimiento) {
		Calendar calendarioNacimiento = Calendar.getInstance();
		calendarioNacimiento.setTime(fechaNacimiento);
		int dianacimiento = calendarioNacimiento.get(Calendar.DAY_OF_YEAR);

		Calendar calendariohoy = Calendar.getInstance();
		int diaHoy = calendariohoy.get(Calendar.DAY_OF_YEAR);
		if (dianacimiento - diaHoy == 0) {// Si la resta de días que llevamos de
											// año es 0, significa que hemos
											// cumplido años
			return calendariohoy.get(Calendar.YEAR) - calendarioNacimiento.get(Calendar.YEAR);

		} else {

			return calendariohoy.get(Calendar.YEAR) - calendarioNacimiento.get(Calendar.YEAR) - 1;

		}

	}
	
	public static Map<String, String> read(String file) throws IOException {
		CSVReader reader = null;
		Map<String, String> map = new HashMap<String, String>();

		reader = new CSVReader(new FileReader(file));

		List<String[]> datos = reader.readAll();

		for (String[] linea : datos) {
			map.put(linea[0], linea[1]);
		}

		reader.close();

		return map;
	}

}
