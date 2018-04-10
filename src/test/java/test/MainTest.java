package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import asw.Application;
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void test1() {
		//Comprobar conexion
		String userURI = base.toString() + "incidence/send";
		ResponseEntity<String> response = template.postForEntity(userURI, new SendIncidence("paco@hotmail.com","123456"), String.class);
		assertThat(response.getBody(), equalTo("{\"kindCode\":1,\"kind\":\"1\",\"name\":\"Paco Gómez\",\"location\":\"41,40338, 2,17403\",\"id\":\"12345678A\",\"email\":\"paco@hotmail.com\"}"));
	}
	
	@Test
	public void test2() {
		//Comprobar conexion
		String userURI = base.toString() + "incidence/send";
		ResponseEntity<String> response = template.postForEntity(userURI, new SendIncidence("paco@hotmail.com","1234s56"), String.class);
		assertThat(response.getBody(), equalTo("{\"reason\":\"Password do not match\"}"));
	}
	
	
	@Test
	public void test3() {
		//Comprobar conexion
		String userURI = base.toString() + "incidence/send";
		ResponseEntity<String> response = template.postForEntity(userURI, new SendIncidence("asdasd@hotmail.com","234"), String.class);
		assertThat(response.getBody(), equalTo("{\"reason\":\"User not found\"}"));
	}
	
	public class SendIncidence {
		
		
		public SendIncidence(String usuario, String password) {
			super();
			this.usuario = usuario;
			this.password = password;
		}

		public String usuario, password;

		public String getUsuario() {
			return usuario;
		}

		public String getPassword() {
			return password;
		}
		
	}
	
}
