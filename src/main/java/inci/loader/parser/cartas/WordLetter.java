package inci.loader.parser.cartas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import inci.loader.model.Ciudadano;
import inci.loader.model.Usuario;

public class WordLetter extends Letter {

	public void createLetter(Usuario user) {
		XWPFDocument documento = new XWPFDocument();
		File folder = new File("carta/word");
		folder.mkdir();
		try {
			FileOutputStream carta = new FileOutputStream("cartas/word/" + user.getCodigo() + ".docx");
			XWPFParagraph paragraph = documento.createParagraph();
			XWPFRun run = paragraph.createRun();
			if (user instanceof Ciudadano) {
				run.setText("Usuario: " + ((Ciudadano) user).getUsername());
				run.addBreak();
				run.setText("Password: " + ((Ciudadano) user).getPassword());
			}
			documento.write(carta);

			documento.close();

			carta.close();

			System.out.println("Se ha generado la carta " + user.getCodigo() + ".docx correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
