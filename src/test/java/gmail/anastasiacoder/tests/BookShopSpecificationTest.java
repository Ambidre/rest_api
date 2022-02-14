package gmail.anastasiacoder.tests;

import gmail.anastasiacoder.annotations.JiraIssue;
import gmail.anastasiacoder.annotations.JiraIssues;
import gmail.anastasiacoder.annotations.Layer;
import gmail.anastasiacoder.annotations.Microservice;
import gmail.anastasiacoder.test_base.ApiRequestsBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Layer("REST")
@Owner("AnotherQA")
@Story("BookShop")
@Feature("BookShopWithSpec")
@JiraIssues({@JiraIssue("HOMEWORK-335")})
@Tag("STAGE")
public class BookShopSpecificationTest extends ApiRequestsBase {

    String userLoginData = "{\"userName\": \"alex\"," +
            "  \"password\": \"asdsad#frew_DFS2\"}";


    @DisplayName("Авторизация со спецификацией")
    @Microservice("Authorization")
    @Tag("API")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void authorizeWithSpecificationTest() {
        step("Check API user's authorize with specification", () -> {
            given()
                    .spec(booksShopRequest)
                    .body(userLoginData)
                    .when()
                    .post("/Account/v1/GenerateToken")
                    .then()
                    .log().body()
                    .spec(successResponseSpec)
                    .body("status", is("Success"))
                    .body("result", is("User authorized successfully."));
        });
    }
}