package utils;

import inciDashboard.entities.User;
import inciDashboard.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.PO_LoginView;

public class Login {

    User user;
    private WebDriver webDriver;
    String url = "http://localhost:8090/";

    public Login(String email, String password) {
        user = new User();
        user.setEmail(email);
        user.setPassword(password);

        webDriver = new HtmlUnitDriver();
    }

    public void login() {
        webDriver.get(url);
        PO_LoginView.fillForm(webDriver, user.getEmail(), user.getPassword());
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
