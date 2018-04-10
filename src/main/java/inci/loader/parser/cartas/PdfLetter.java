package inci.loader.parser.cartas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import inci.loader.model.*;

public class PdfLetter extends Letter{
	
	public  void createLetter(Usuario Usuario){
		Document document = null;
		FileOutputStream letter = null;
		try {
		letter = new FileOutputStream(
				"cartas/pdf/" + Usuario.getCodigo() + ".pdf");
		document = new Document();
		PdfWriter.getInstance(document, letter);
		document.open();
		if(Usuario instanceof Ciudadano) {
			document.add(new Paragraph("Usuario: " + ((Ciudadano)Usuario).getUsername()
			+ "\n Password: " + ((Ciudadano)Usuario).getPassword()));
		}
		if(Usuario instanceof Sensor) {
			document.add(new Paragraph("Usuario: " + ((Sensor)Usuario).getNombre()
			+ "\n Código: " + ((Sensor)Usuario).getCodigo()));
		}
		if(Usuario instanceof Entidad) {
			document.add(new Paragraph("Usuario: " + ((Entidad)Usuario).getNombre()
			+ "\n Código: " + ((Entidad)Usuario).getCodigo()));
		}
				
		
		System.out.println("Se ha generado la carta " + Usuario.getCodigo() + ".pdf correctamente.");
		} catch(DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		finally {
			document.close();
		}
		
		
	}
}
