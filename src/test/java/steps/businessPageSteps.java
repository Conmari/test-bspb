package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import scari.corp.BrowserStackPage.BrowserStackBusinessPage;
import scari.corp.utils.ChromeDriverFactory;
import scari.corp.utils.WebDriverUtils;


@DisplayName("Тесты для сайта БСПБ")
public class businessPageSteps {
    private WebDriver driver;
    BrowserStackBusinessPage objBrowserStackBusinessPage;
    private final String BASE_URL = "https://www.bspb.ru/business";

    @Before
    public void setUp() {
        driver = ChromeDriverFactory.createDriver();
        objBrowserStackBusinessPage = new BrowserStackBusinessPage(driver);
        WebDriverUtils.safeGet(driver, BASE_URL);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step
    @Когда("я ввожу свои фио {string}")
    public void я_ввожу_свои_фио(String fio) {
        objBrowserStackBusinessPage.setNameInput(fio);
    }

    @И("я ввожу свой телефон {string}")
    public void я_ввожу_свой_телефон(String phone) {
        objBrowserStackBusinessPage.setPhoneInput(phone);
    }

    @Step
    @И("я ввожу дополнительные данные {string}")
    public void я_ввожу_дополнительные_данные(String innNumber) {
        objBrowserStackBusinessPage.setInnInput(innNumber);
    }

    @Step
    @И("я ввожу дополнительные данные {string} usingRecursiveComparison")
    public void я_ввожу_дополнительные_данные_usingRecursiveComparison(String innNumber) {
        objBrowserStackBusinessPage.setInnInput(innNumber);
    }

    @Step
    @Тогда("поля фио и дополнительные данные заполнены")
    public void поляФиоИДополнительныкДанныеЗаполненны() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(objBrowserStackBusinessPage.getNameInput())
                .as("Проверяем, что имя заполнено")
                .isNotBlank();

        softly.assertThat(objBrowserStackBusinessPage.getPhoneInput())
                .as("Проверяем, что телефон заполнен")
                .isNotBlank();

        softly.assertThat(objBrowserStackBusinessPage.getInnInput())
                .as("Проверяем, что ИНН заполнен")
                .isNotBlank();

        softly.assertAll();  // выбросит AssertionError с подробностями
    }

    @Step
    @Тогда("я могу нажать отправить")
    public void я_могу_нажать_отправить() {
        AssertionsForClassTypes.assertThat(objBrowserStackBusinessPage.verifySubmitButtonIiEnabled())
                .as("Кнопка отправить должна быть доступна")
                .isTrue();
    }
}
