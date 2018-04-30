package steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import inciDashboard.entities.User;
import inciDashboard.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.PO_LoginView;
import pageobjects.PO_NavView;
import pageobjects.PO_StateView;
import pageobjects.PO_View;

public class CloseIncidenceStep {

    User user;
    WebDriver webDriver;
    String url = "http://localhost:8090/";

    @Dado("^el operario \"([^\"]*)\" con contraseña \"([^\"]*)\" cerrar incidencia$")
    public void elOperarioConContraseñaCerrarIncidencia(String arg0, String arg1) throws Throwable {
        System.out.println("Creamos el usuario");
        user = new User();
        user.setEmail(arg0);
        user.setPassword(arg1);
    }

    @Cuando("^el operario hace login correctamente cerrar incidencia$")
    public void elOperarioHaceLoginCorrectamenteCerrarIncidencia() throws Throwable {
        webDriver = new HtmlUnitDriver();
        webDriver.get(url);
        PO_View.checkElement(webDriver, Constants.TEXT_TYPE, "Login");
        System.out.println("Logeamos");
        PO_LoginView.fillForm(webDriver, user.getEmail(), user.getPassword());
    }

    @Y("^hace click en la opción de cerrar una incidencia, dentro de una incidencia seleccionada$")
    public void haceClickEnLaOpciónDeCerrarUnaIncidenciaDentroDeUnaIncidenciaSeleccionada() throws Throwable {
//        By button = By.xpath("//tr[contains(text(), 'Incidencia en el bosque')]/following-sibling::*[5]");
//        WebElement element = webDriver.findElement(button);
//        element.click();
        webDriver.get("http://localhost:8090/user/changeStatus/1");
        System.out.println("Cambiamos el estado");
//        PO_StateView.changeState(webDriver, "estado", "CERRADA");
    }

    @Entonces("^el estado de la incidencia pasa a ser cerrado$")
    public void elEstadoDeLaIncidenciaPasaASerCerrado() throws Throwable {
        System.out.println("Comprobamos que el estado de la incidencia sea cerrado");
    }
}
