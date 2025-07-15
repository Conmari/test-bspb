package steps;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import scari.corp.utils.ChromeDriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class businessPageSteps {
    private WebDriver driver;
    private final String BASE_URL = "https://www.bspb.ru/business";

    @Before
    public void setUp() {
        driver = ChromeDriverFactory.createDriver();
    }
    

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Когда("я ввожу свои данные")
    public void я_ввожу_свои_данные() {
        driver.get(BASE_URL);
            
        WebElement nameInput = driver.findElement(By.id("FIRSTNAME-Имя"));
        nameInput.sendKeys("Тест Тнстович");

        WebElement emailInput = driver.findElement(By.name("PHONENUMBER-Телефон"));
        emailInput.sendKeys("921457896354123");
    }

    @И("я ввожу дополнительные данные")
    public void я_ввожу_дополнительные_данные() {
        WebElement phoneInput = driver.findElement(By.id("INN-ИНН"));
        phoneInput.sendKeys("123456789");
    }

    @Тогда("я могу нажать отправить")
    public void я_могу_нажать_отправить() {
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[5]/div/div/div/div/form/div/div[13]/button"));

        assertTrue(submitButton.isEnabled(), "Кнопка отправить должна быть доступна");

        
    }

}
