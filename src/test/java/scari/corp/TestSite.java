package scari.corp;

import org.junit.jupiter.api.*;
import scari.corp.utils.ChromeDriverFactory;
import scari.corp.utils.WebDriverUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

@DisplayName("Тесты для сайта БСПБ")
@Disabled("сейчас гоняют cucumber")
public class TestSite {

    private WebDriver driver;
    private final String BASE_URL = "https://www.bspb.ru";

    @BeforeAll
    public static void initAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = ChromeDriverFactory.createDriver();
        driver.manage().window().maximize();
        WebDriverUtils.safeGet(driver, BASE_URL);
    }

    @Test
    @DisplayName("Тест отображения кнопки Войти на главном экране")
    public void buttonDisplayLogin() {
        WebElement login = driver.findElement(By.id("popover-trigger-:R3adt9jltmH1:"));
        assertTrue(login.isDisplayed(), "Кнопка войти должна отображаться");
    }

    @Test
    @DisplayName("Тест ссылка контакты вёдёт на страницу с контактами")
    public void linkContact() {
        String waitURL = BASE_URL + "/feedback";
        WebElement contact = driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[2]/div[1]/nav/div[4]/a[1]"));
        contact.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlContains(waitURL));
        assertEquals(waitURL, driver.getCurrentUrl(), "URL должен быть одинаков");
    }

    @Test
    @DisplayName("Тест кнопка ВОЙТИ -> Интернет банк ФЛ открывает страницу Вход в интернет-банк")
    public void openLoginFLPage() {
        String waitRexpURL = "https://i\\.bspb\\.ru/auth.*";
        WebElement button = driver.findElement(By.id("popover-trigger-:R3adt9jltmH1:"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buttonFLPage = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@id=\"popover-content-:R3adt9jltmH1:\"]/div/a[1]")));
        buttonFLPage.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String originalWindow = driver.getWindowHandle();
        driver.getWindowHandles().stream()
                .filter(windowHandle -> !windowHandle.equals(originalWindow))
                .findFirst()
                .ifPresent(windowHandle -> driver.switchTo().window(windowHandle));
        wait.until(driver -> driver.getCurrentUrl().matches(waitRexpURL));
        assertTrue(driver.getCurrentUrl().matches(waitRexpURL), "URL должен начинаться " + waitRexpURL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
