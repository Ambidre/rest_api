package gmail.anastasiacoder.tests;

import gmail.anastasiacoder.test_base.ApiRequestsBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Owner("AnotherQA")
@Story("BookShop")
@Feature("BookShopWithSpec")
public class BookShopSpecificationTest extends ApiRequestsBase {

    String userLoginData = "{\"userName\": \"alex\"," +
            "  \"password\": \"asdsad#frew_DFS2\"}";

    @Tag("API")
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