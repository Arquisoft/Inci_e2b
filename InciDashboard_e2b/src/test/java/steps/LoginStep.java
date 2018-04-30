package steps;

import cucumber.api.PendingException;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import inciDashboard.entities.User;
import inciDashboard.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.PO_LoginView;
import pageobjects.PO_View;

public class LoginStep {

    User user;
    WebDriver webDriver;
    String url = "http://localhost:8090/";

    @Dado("^un correo \"([^\"]*)\" y una contrasena \"([^\"]*)\"$")
    public void unCorreoYUnaContrasena(String arg0, String arg1) throws Throwable {
        System.out.println("Creamos el usuario");
        user = new User();
        user.setEmail(arg0);
        user.setPassword(arg1);
    }

    @Cuando("^el operario se logea correctamente$")
    public void elOperarioSeLogeaCorrectamente() throws Throwable {
        webDriver = new HtmlUnitDriver();
        webDriver.get(url);
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Login");
        System.out.println("Logeamos");
        PO_LoginView.fillForm(webDriver, user.getEmail(), user.getPassword());
    }

    @Entonces("^visualiza la pagina principal$")
    public void visualizaLaPaginaPrincipal() throws Throwable {
        System.out.println("Comprobamos que esta en la pagina principal");
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Incidencias");
    }
}
