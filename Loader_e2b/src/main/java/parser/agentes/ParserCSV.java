package parser.agentes;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

public class ParserCSV {

	public Map<String, String> read(String file) throws IOException {
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
