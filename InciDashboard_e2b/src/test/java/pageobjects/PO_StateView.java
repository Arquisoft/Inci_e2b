package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PO_StateView {
    static public void changeState(WebDriver driver, String id, String state) {
        Select select = new Select(driver.findElement(By.id(id)));
        select.deselectAll();
        select.selectByVisibleText(state);
        By button = By.className("btn");
        driver.findElement(button).click();
    }
}
