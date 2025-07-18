package steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scari.corp.utils.ChromeDriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import scari.corp.utils.WebDriverUtils;


@DisplayName("Тесты для сайта БСПБ")
public class businessPageSteps {
    private WebDriver driver;
    private final String BASE_URL = "https://www.bspb.ru/business";

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

    @Step
    @Когда("я ввожу свои данные")
    public void я_ввожу_свои_данные() {
            
        WebElement nameInput = driver.findElement(By.id("FIRSTNAME-Имя"));
        nameInput.sendKeys("Тест Тнстович");

        WebElement emailInput = driver.findElement(By.name("PHONENUMBER-Телефон"));
        emailInput.sendKeys("921457896354123");
    }


    @Step
    @И("я ввожу дополнительные данные")
    public void я_ввожу_дополнительные_данные() {
        WebElement phoneInput = driver.findElement(By.id("INN-ИНН"));
        phoneInput.sendKeys("123456789");
        assertThat(phoneInput.getAttribute("value").length())
                .as("Проверяем, что длина введённого значения составляет 6 символов")
                .isEqualTo(6);
    }

    @Step
    @Когда("я ввожу дополнительные данные софтассерт")
    public void я_ввожу_дополнительные_данные_софтассерт() {
        SoftAssertions softly = new SoftAssertions();

        WebElement phoneInput = driver.findElement(By.id("INN-ИНН"));
        softly.assertThat(phoneInput.getAttribute("value"))
                .as("Проверяем длину введённого значения")
                .isEqualTo(6);
        String inputValue = "123456789";
        phoneInput.sendKeys(inputValue);

        String enteredValue = phoneInput.getAttribute("value");
        softly.assertThat(enteredValue).isEqualTo(inputValue);
        softly.assertThat(enteredValue.length())
                .as("Проверяем длину введённого значения")
                .isEqualTo(6);

        softly.assertAll();  // выбросит AssertionError с подробностями
    }


    @Step
    @Тогда("я могу нажать отправить")
    public void я_могу_нажать_отправить() {
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[5]/div/div/div/div/form/div/div[13]/button"));

        assertThat(submitButton.isEnabled())
                .as("Кнопка отправить должна быть доступна")
                .isTrue();
    }
}
