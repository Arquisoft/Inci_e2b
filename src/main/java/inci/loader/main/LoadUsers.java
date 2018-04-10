package inci.loader.main;

import com.lowagie.text.DocumentException;

import inci.loader.parser.ReaderSingleton;

/**
 * Main application
 * 
 * @author E2B
 *
 */
public class LoadUsers {

	public static void main(String... args) throws DocumentException  {	
		final LoadUsers runner = new LoadUsers();
		runner.run(args);
	}

	private void run(String... args) throws DocumentException{
		if (args.length == 0 || args[0].equals("--help"))
			showHelp();
		else if (args[0].equals("info"))
			showInfo();
		else if (args[0].equals("load")) {
			if (args.length < 2)
				System.err.println(
						"La operación load necesita al menos otro argumento con la ubicación del fichero");
			else {
				//for (int i = 1; i <= args.length - 1; i++)
					//ReaderSingleton.getInstance().loadFile(args[i]);
				System.out.println(args[1]+" - "+args[2]);
				ReaderSingleton.getInstance().loadFile(args[1],args[2]);
			}
		}
	}

	private void showInfo() {
		System.out.println("Proyecto Loader e2B");
		System.out.println("El objetivo de este proyecto es actuar como módulo de carga de "
				+ "usuarios para un sistema de análisis de incidencias");
		System.out.println("Realizado por el grupo e2B compuesto por: ");
		System.out.println("\tPablo Hevia Viejo (UO251259)");
		System.out.println("\tPelayo Martínez Capela (UO250985)");
		System.out.println("\tGemma González Gil (UO236976)");
		System.out.println("\tErik Gabriel González García (UO224164)");
		System.out.println("Para más informacion consultar el repositorio en github con la url "
				+ "https://github.com/Arquisoft/Loader_e2b");

	}

	private void showHelp() {
		System.out.println("Manual de ayuda para el uso de la aplicación");
		System.out.println("La aplicación tiene implementadas las operaciones info, load y help");
		System.out.println("\tinfo: Muestra información relacionada con el proyecto, como los autores");
		System.out.println(
				"\tload [file]: Permite cargar un conjunto de ficheros excel con usuarios en la base de datos");
		System.out.println("\t--help: Muestra este menú de ayuda. Si no se proporcionan parámetros "
				+ "a la aplicación se mostrará este menú");
	}
}
