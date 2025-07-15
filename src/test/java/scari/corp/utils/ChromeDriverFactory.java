package scari.corp.utils;


import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory {


    public static WebDriver createDriver() {
        try {
            // System.setProperty("webdriver.chrome.driver", "путь/к/chromedriver.exe");
            return new ChromeDriver();

        } catch (SessionNotCreatedException e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("cannot find Chrome binary")) {
                System.err.println("Chrome не найден. Пропускаем тесты.");
                Assumptions.assumeTrue(false, "Chrome не найден. Пропускаем тесты.");
                return null;
            }
            else {
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } 
    }
    
}
