package steps;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import scari.corp.utils.ChromeDriverFactory;
import scari.corp.utils.WebDriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class mainPageSteps {
    private WebDriver driver;
    private final String BASE_URL = "https://www.bspb.ru";

    @Before
    public void setUp() {
        driver = ChromeDriverFactory.createDriver();
        WebDriverUtils.safeGet(driver, BASE_URL);
    }
    

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Когда("я открываю главную страницу")
    public void я_открываю_главную_страницу() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Тогда("отображается кнопка Войти")
    public void отображается_кнопка_Войти_на_главном_экране() {
        WebElement login = driver.findElement(By.id("popover-trigger-:R3adt9jltmH1:"));
        assertTrue(login.isDisplayed(), "Кнопка войти должна отображаться");
    }

    @Когда("я открываю главную страницу на телефоне")
    public void я_открываю_главную_страницу_на_телефоне() {
        driver.manage().window().setSize(new Dimension(375, 812));
        driver.get(BASE_URL);
    }

    @Тогда("кнопка Войти не отображается")
    public void кнопка_Войти_не_отображается() {
        WebElement login = driver.findElement(By.id("popover-trigger-:R3adt9jltmH1:"));
        assertFalse(login.isDisplayed(), "Кнопка войти должна отображаться");
    }

}
