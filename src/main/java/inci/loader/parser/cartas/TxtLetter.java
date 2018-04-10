package inci.loader.parser.cartas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import inci.loader.model.Ciudadano;
import inci.loader.model.Usuario;

public class TxtLetter extends Letter{
	

	public void createLetter(Usuario user){
		try {
			File letter = new File("cartas/txt/" + user.getCodigo() + ".txt");
			Writer writer = new FileWriter(letter);
			if(user instanceof Ciudadano) {
				writer.write("Usuario: " + ((Ciudadano)user).getUsername() + "\n" + "Password: "
						+ ((Ciudadano)user).getPassword());
				writer.close();
			}
			
			System.out.println("Se ha generado la carta " + user.getCodigo() + ".txt correctamente.");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
