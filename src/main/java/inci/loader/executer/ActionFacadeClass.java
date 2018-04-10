package inci.loader.executer;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.lowagie.text.DocumentException;

import inci.loader.dbupdate.Insert;
import inci.loader.dbupdate.InsertP;
import inci.loader.model.Usuario;

public class ActionFacadeClass implements ActionFacade {

	@Override
	public void saveData(Usuario user) throws FileNotFoundException, DocumentException, IOException {
		Insert insert = new InsertP();
		insert.save(user);
			
	}
}