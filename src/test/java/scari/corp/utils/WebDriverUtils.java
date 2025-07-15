package scari.corp.utils;

import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class WebDriverUtils {
/**
     * Загружает страницу с обработкой ошибки отсутствия интернета.
     * При отсутствии интернета тест будет пропущен.
     *
     * @param driver WebDriver
     * @param url    URL страницы
     */
    public static void safeGet(WebDriver driver, String url) {
        try {
            driver.get(url);
        } catch (WebDriverException e) {
            if (e.getMessage() != null && e.getMessage().contains("ERR_INTERNET_DISCONNECTED")) {
                System.err.println("Нет подключения к интернету. Пропускаем тест.");
                Assumptions.assumeTrue(false, "Нет подключения к интернету. Пропускаем тест.");
            } else {
                throw e;
            }
        }
    }
}
