package inci.agents;

import java.text.ParseException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import asw.dbManagement.model.Agent;
import asw.dbManagement.repository.AgentRepository;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initDB(AgentRepository repository) throws ParseException {
		return (args) -> {
			// Inserción en la base de datos
			repository.save(new Agent("Paco Gómez", "123456", "paco@hotmail.com", "41,40338, 2,17403","12345678A",
					1));

			// Inserción en la base de datos
			repository.save(new Agent("Pepe Fernández", "123456",  "pepe@gmail.com","4,45328, 2,17403", "87654321B",
					1));

			repository.save(new Agent("Carmen López", "123456",  "carmen@yahoo.com","12,4338, 2,17403", "11223344C",
					1));

			// Inserción en la base de datos
			repository.save(new Agent("Isabel Rodríguez", "123456",  "isabel@gmail.com","41,338, 2,17403", "22334455D",
					1));

			// ADMIN
			// Inserción en la base de datos
			repository.save(new Agent("María Sánchez", "123456",  "maria@gmail.com","21,40338, 2,17403", "33445566E",
					1));

			// POLITICO
			// Inserción en la base de datos
			repository.save(new Agent("Jose Ballesteros", "123456",  "jose@gmail.com","53,403, 2,17403", "44556677F",
					1));

		};
	}
}