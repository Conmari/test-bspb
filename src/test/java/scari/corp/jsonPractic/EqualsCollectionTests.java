package scari.corp.jsonPractic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import scari.corp.api.ApiTest;
import scari.corp.jsonPractic.officeRate.CursRate;
import scari.corp.jsonPractic.officeRate.Item;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Сравнение коллекции")
@Tag("@checkResponseOfficeRate")
public class EqualsCollectionTests {
    private static final String urlJson = "https://www.bspb.ru/api/currency-service/office-rates";
    private static final String pathJson = "1office-rates-response.json";

    private static CursRate expected;
    private static CursRate actual;

    @BeforeAll
    public static void initAll() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ApiTest apiTest = new ApiTest();

        String jsonFile = apiTest.getJsonOfficeRate(urlJson);

        expected = mapper.readValue(jsonFile, CursRate.class);
        actual = mapper.readValue(new File(pathJson), CursRate.class);
    }

    @Test
    @DisplayName("Сравнение Json ответ от OfficeRate Contains")
    public void checkResponseOfficeRateContains() {
        assertThat(expected.getItems())
                .extracting(Item::getAddress)
                .anyMatch(address -> address.contains("Санкт-Петербург"));
    }

    @Test
    @DisplayName("Сравнение Json ответ от OfficeRate Contains объекта нет")
    public void checkResponseOfficeRateContainsFalse() {
        assertThat(expected.getItems())
                .extracting(Item::getAddress)
                .anyMatch(address -> address.contains("Я упаду)"));
    }

    @Test
    @DisplayName("Сравнение Json с новым ответом от OfficeRate usingRecursiveComparison")
    public void checkResponseOfficeRateUsingRecursiveComparison() {
        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Сравнение Json с новым ответом от OfficeRate usingRecursiveComparison при помощи Листа")
    public void checkResponseOfficeRateUsingRecursiveComparisonList() {
        assertThat(actual.getItems())
                .usingRecursiveComparison()
                .isEqualTo(expected.getItems());
    }

    @Test
    @DisplayName("Сравнение Json с новым ответом от OfficeRate usingRecursiveComparison при помощи Листа игнор полей")
    public void checkResponseOfficeRateUsingRecursiveComparisonListIgnorField() {
        assertThat(actual.getItems()).as("check Items")
                .usingRecursiveComparison()
                .ignoringFields("rates.buyRate", "rates.cbRate", "rates.sellRate")
                .isEqualTo(expected.getItems());
    }

    @Test
    @DisplayName("Сравнение Json с новым ответом от OfficeRate usingRecursiveComparison при помощи Листа игнор полей overridingErrorMessage")
    public void checkResponseOfficeRateUsingRecursiveComparisonListIgnorFieldOverridingMessage() {
        assertThat(actual.getItems())
                .overridingErrorMessage("Ошибка переопределена %s", actual)
                .as("check Items")
                .usingRecursiveComparison()
                .isEqualTo(expected.getItems());
    }

    @Test
    @DisplayName("Сравнение Json с новым ответом от OfficeRate usingRecursiveComparison при помощи Листа игнор полей withFailMessage")
    public void checkResponseOfficeRateUsingRecursiveComparisonListIgnorFieldWithFailMessage() {
        assertThat(actual.getItems())
                .withFailMessage("Ошибка переопределена %s", actual)
                .as("check Items")
                .usingRecursiveComparison()
                .isEqualTo(expected.getItems());
    }

    @Test
    @DisplayName("Сравнение Json с новым ответом от OfficeRate AnySatisfy хотя бы один в списке верен")
    public void checkResponseOfficeRateAnySatisfy() {
        assertThat(actual.getItems())
                .extracting(Item::getAddress)
                .anySatisfy(address -> assertThat(address).startsWith("63"));
    }
}
