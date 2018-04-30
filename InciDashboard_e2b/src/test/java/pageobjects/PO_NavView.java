package pageobjects;

import inciDashboard.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

//import static org.testng.Assert.assertTrue;

import static junit.framework.TestCase.assertTrue;

public class PO_NavView extends PO_View {
    public static void clickOption(WebDriver driver, String textoption, String criterio, String textoDestino) {
        List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textoption, getTimeout());
        assertTrue(elementos.size() == 1);
        elementos.get(0).click();
        elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
        assertTrue(elementos.size() == 1);
    }

    public static void changeIdiom(WebDriver driver, String textLanguage) {
        List<WebElement> elements = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
        elements.get(0).click();
        elements = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
        elements = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
        elements.get(0).click();
    }
}
