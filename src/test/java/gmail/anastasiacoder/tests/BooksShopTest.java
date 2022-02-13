package gmail.anastasiacoder.tests;

import gmail.anastasiacoder.models.books_shop.GenerateToken;
import gmail.anastasiacoder.models.books_shop.UserLoginData;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static gmail.anastasiacoder.filters.CustomLogFilter.customLogFilter;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("Ambidre")
@Story("BookShop")
@Feature("BookShop")
@Tag("API")
public class BooksShopTest {

    private UserLoginData userLoginData = new UserLoginData();

    public UserLoginData setUserLoginData() {
        userLoginData.setUserName("alex");
        userLoginData.setPassword("asdsad#frew_DFS2");
        return userLoginData;
    }

    String expectedStatus = "Success";
    String expectedResult = "User authorized successfully.";

    @BeforeAll
    static void prepare() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    void noLogsTest() {
        step("Check API books without logs", () -> {
            given()
                    .get("/BookStore/v1/Books")
                    .then()
                    .body("books", hasSize(greaterThan(0)));
        });
    }

    @Test
    void withAllLogsTest() {
        step("Check API books with all logs", () -> {
            given()
                    .log().all()
                    .get("/BookStore/v1/Books")
                    .then()
                    .log().all()
                    .body("books", hasSize(greaterThan(0)));
        });
    }

    @Test
    void withSomeLogsTest() {
        step("Check API books with some logs", () -> {
            given()
                    .log().uri()
                    .log().body()
                    .get("/BookStore/v1/Books")
                    .then()
                    .log().body()
                    .body("books", hasSize(greaterThan(0)));
        });
    }

    @Test
    @Disabled
    void authorizeApiTest() {
        step("Check API user's authorize", () -> {
            GenerateToken generateToken =
                    given()
                            .contentType("application/json")
                            .accept("application/json")
                            .body(setUserLoginData())
                            .when()
                            .log().uri()
                            .log().body()
                            .post("/Account/v1/GenerateToken")
                            .then()
                            .log().body()
                            .extract().as(GenerateToken.class);

            assertEquals(expectedStatus, generateToken.getStatus());
            assertEquals(expectedResult, generateToken.getResult());
        });
    }

    @Test
    void authorizeWithListenerTest() {
        step("Check API user's authorize with Listener", () -> {
            GenerateToken generateToken =
                    given()
                            .filter(new AllureRestAssured())
                            .contentType("application/json")
                            .accept("application/json")
                            .body(setUserLoginData())
                            .when()
                            .log().uri()
                            .log().body()
                            .post("/Account/v1/GenerateToken")
                            .then()
                            .log().body()
                            .extract().as(GenerateToken.class);

            assertEquals(expectedStatus, generateToken.getStatus());
            assertEquals(expectedResult, generateToken.getResult());
        });
    }

    @Test
    void authorizeWithTemplatesTest() {
        step("Check API user's authorize with custom log filter", () -> {
            GenerateToken generateToken =
                    given()
                            .filter(customLogFilter().withCustomTemplates())
                            .contentType("application/json")
                            .accept("application/json")
                            .body(setUserLoginData())
                            .when()
                            .log().uri()
                            .log().body()
                            .post("/Account/v1/GenerateToken")
                            .then()
                            .log().body()
                            .extract().as(GenerateToken.class);

            assertEquals(expectedStatus, generateToken.getStatus());
            assertEquals(expectedResult, generateToken.getResult());
        });
    }
}
