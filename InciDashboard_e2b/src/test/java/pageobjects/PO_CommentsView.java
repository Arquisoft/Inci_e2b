package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_CommentsView {
    static public void fillForm(WebDriver driver, String texto) {
        WebElement comment = driver.findElement(By.name("texto"));
        comment.click();
        comment.clear();
        comment.sendKeys(texto);
        By button = By.className("btn");
        driver.findElement(button).click();
    }
}
