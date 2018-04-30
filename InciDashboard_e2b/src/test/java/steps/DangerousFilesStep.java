package steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import org.openqa.selenium.WebDriver;
import utils.Login;

public class DangerousFilesStep {

    private Login login;
    private WebDriver driver;

    @Dado("^el operario \"([^\"]*)\" con contraseña \"([^\"]*)\" comprobar campos$")
    public void elOperarioConContraseñaComprobarCampos(String arg0, String arg1) throws Throwable {
        login = new Login(arg0, arg1);
    }

    @Cuando("^el operario hace login correctamente comprobar campos$")
    public void elOperarioHaceLoginCorrectamenteComprobarCampos() throws Throwable {
        login.login();
        driver = login.getWebDriver();
    }

    @Y("^hace click en el boton de peligro$")
    public void haceClickEnElBotonDePeligro() throws Throwable {
        driver.get("http://localhost:8090/incidence/1");
    }

    @Entonces("^puede visualizar los campos peligrosos$")
    public void puedeVisualizarLosCamposPeligrosos() throws Throwable {
    }
}
