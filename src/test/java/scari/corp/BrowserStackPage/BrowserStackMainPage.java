package scari.corp.BrowserStackPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserStackMainPage {
    WebDriver driver;
    By loginButton = By.id("popover-trigger-:R3adt9jltmH1:");

    public BrowserStackMainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Проверка, что кнопка login отображается
    public boolean verifyLoginButtonIsDisplayed() {
        try {
            WebElement login = driver.findElement(loginButton);
            return login.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Элемент не найден — значит не отображается
            return false;
        }
    }
}
