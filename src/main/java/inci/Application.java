package inci;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lowagie.text.DocumentException;

import inci.agents.AgentRepository;
import inci.agents.Bean;
import inci.agents.CommandLineRunner;
import inci.loader.main.LoadUsers;
import inci.loader.parser.ReaderSingleton;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
	final LoadUsers runner = new LoadUsers();
	runner.run(args);
    }

    @Bean
    public CommandLineRunner initDB(AgentRepository repository) throws ParseException {
	return (args) -> {
	    // Inserción en la base de datos
	    repository.save(new Agent("Paco Gómez", "123456", "paco@hotmail.com", "41,40338, 2,17403", "12345678A", 1));

	    // Inserción en la base de datos
	    repository
		    .save(new Agent("Pepe Fernández", "123456", "pepe@gmail.com", "4,45328, 2,17403", "87654321B", 1));

	    repository
		    .save(new Agent("Carmen López", "123456", "carmen@yahoo.com", "12,4338, 2,17403", "11223344C", 1));

	    // Inserción en la base de datos
	    repository.save(
		    new Agent("Isabel Rodríguez", "123456", "isabel@gmail.com", "41,338, 2,17403", "22334455D", 1));

	    // ADMIN
	    // Inserción en la base de datos
	    repository
		    .save(new Agent("María Sánchez", "123456", "maria@gmail.com", "21,40338, 2,17403", "33445566E", 1));

	    // POLITICO
	    // Inserción en la base de datos
	    repository
		    .save(new Agent("Jose Ballesteros", "123456", "jose@gmail.com", "53,403, 2,17403", "44556677F", 1));

	};
    }

    private void run(String... args) throws DocumentException {
	if (args.length == 0 || args[0].equals("--help"))
	    showHelp();
	else if (args[0].equals("info"))
	    showInfo();
	else if (args[0].equals("load")) {
	    if (args.length < 2)
		System.err.println("La operación load necesita al menos otro argumento con la ubicación del fichero");
	    else {
		// for (int i = 1; i <= args.length - 1; i++)
		// ReaderSingleton.getInstance().loadFile(args[i]);
		System.out.println(args[1] + " - " + args[2]);
		ReaderSingleton.getInstance().loadFile(args[1], args[2]);
	    }
	}
    }

    private void showInfo() {
	System.out.println("Proyecto Inci e2");
	System.out.println("El objetivo de este proyecto es actuar como módulo de carga de "
		+ "usuarios para un sistema de análisis de incidencias");
	System.out.println("Realizado por el grupo e2B compuesto por: ");
	System.out.println("\tPablo Hevia Viejo (UO251259)");
	System.out.println("\tPelayo Martínez Capela (UO250985)");
	System.out.println("\tGemma González Gil (UO236976)");
	System.out.println("\tErik Gabriel González García (UO224164)");
	System.out.println("Para más informacion consultar el repositorio en github con la url "
		+ "https://github.com/Arquisoft/Inci_e2b");

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