package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import scari.corp.BrowserStackPage.BrowserStackMainPage;
import scari.corp.utils.ChromeDriverFactory;
import scari.corp.utils.WebDriverUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class mainPageSteps {
    BrowserStackMainPage objBrowserStackMainPage;
    private WebDriver driver;
    private final String BASE_URL = "https://www.bspb.ru";

    @Before
    public void setUp() {
        driver = ChromeDriverFactory.createDriver();
        objBrowserStackMainPage = new BrowserStackMainPage(driver);
        WebDriverUtils.safeGet(driver, BASE_URL);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step
    @Когда("я открываю главную страницу")
    public void я_открываю_главную_страницу() {
        driver.manage().window().maximize();
    }

    @Step
    @Тогда("отображается кнопка Войти")
    public void отображается_кнопка_Войти_на_главном_экране() {
        assertThat(objBrowserStackMainPage.verifyLoginButtonIsDisplayed())
                .as("Кнопка войти отображаться не должна")
                .isTrue();
    }

    @Step
    @Когда("я открываю главную страницу на телефоне с шириной {int} и высотой {int}")
    public void я_открываю_главную_страницу_на_телефоне(int width, int height) {

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(width)
                .withFailMessage("Ширина должна быть положительной числом, но получено: %d", width)
                .isPositive();

        softly.assertThat(height)
                .withFailMessage("Высота должна быть положительной числом, но получено: %d", height)
                .isGreaterThan(0);

        if (width > 0 && height > 0) {
            try {
                driver.manage().window().setSize(new Dimension(width, height));
            } catch (InvalidArgumentException e) {
                softly.fail("Ошибка установки размера окна: " + e.getMessage());
            }
        } else {
            System.out.println("Установка размера окна пропущена из-за некорректных параметров: width="
                    + width + ", height=" + height);
        }

        softly.assertAll();
    }

    @Step
    @Тогда("кнопка Войти не отображается")
    public void кнопка_Войти_не_отображается() {
        assertThat(objBrowserStackMainPage.verifyLoginButtonIsDisplayed())
                .as("Кнопка войти отображаться не должна")
                .isFalse();
    }
}
