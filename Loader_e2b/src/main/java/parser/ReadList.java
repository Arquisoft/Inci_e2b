package parser;

import java.io.FileNotFoundException;

import com.lowagie.text.DocumentException;

public interface ReadList {
	public void loadExcel(String path) throws FileNotFoundException, DocumentException;

	public void loadCSV(String path) throws DocumentException, FileNotFoundException;
}
