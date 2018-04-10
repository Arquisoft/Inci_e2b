package inci.loader.executer;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.lowagie.text.DocumentException;

import inci.loader.model.Usuario;

public interface ActionFacade {
	public void saveData(Usuario usuario) throws FileNotFoundException, DocumentException, IOException;
	// public void verifySecurity();  Esto es una ampliacion opcional y no se en que consiste
}