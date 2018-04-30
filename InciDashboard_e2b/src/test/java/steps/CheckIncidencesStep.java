package steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import inciDashboard.entities.User;
import inciDashboard.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.PO_LoginView;
import pageobjects.PO_View;
import utils.Login;

public class CheckIncidencesStep {

    User user;
    WebDriver webDriver;
    String url = "http://localhost:8090/";
    private Login login;

    @Dado("^el operario \"([^\"]*)\" con contraseña \"([^\"]*)\" para hitorial$")
    public void elOperarioConContraseñaParaHitorial(String arg0, String arg1) throws Throwable {
        login = new Login(arg0, arg1);
    }

    @Cuando("^el agente hace login correctamente para historial$")
    public void elAgenteHaceLoginCorrectamenteParaHistorial() throws Throwable {
        login.login();
        webDriver = login.getWebDriver();
    }

    @Entonces("^se muestra correctamente el historial de incidencias$")
    public void seMuestraCorrectamenteElHistorialDeIncidencias() throws Throwable {
//        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Incidencia en el bosque");
        webDriver.get("http://localhost:8090/user/listIncidencias");
    }
}
