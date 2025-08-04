package scari.corp.api;

import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Тесты api")
public class ApiTest {

    public String getJsonOfficeRate(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .body(notNullValue())
                .extract()
                .response()
                .asString();
    }

}
