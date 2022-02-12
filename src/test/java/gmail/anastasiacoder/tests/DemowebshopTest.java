package gmail.anastasiacoder.tests;

import com.codeborne.selenide.SelenideElement;
import gmail.anastasiacoder.models.demowebshop.WishList;
import gmail.anastasiacoder.test_base.UiTestBase;
import io.qameta.allure.*;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static gmail.anastasiacoder.filters.CustomLogFilter.customLogFilter;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("nosova")
@Story("DemoWebShop")
@Feature("DemoWebShop")
public class DemowebshopTest extends UiTestBase {

    private String body;

    private RequestSpecification demoWebShopRequest = with()
            .baseUri("http://demowebshop.tricentis.com")
            .filter(customLogFilter().withCustomTemplates())
            .log().all()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    @Test
    @Tag("API")
    void checkWishListAPI() {
        body = "addtocart_53.EnteredQuantity=1";

        String expectedUpdatetopwishlist = "(1)";
        String expectedMessage = "The product has been added to your <a href=\"/wishlist\">wishlist</a>";

        step("Add product to Wishlist", () -> {
            WishList wishList =
                    given()
                            .spec(demoWebShopRequest)
                            .body(body)
                            .when()
                            .post("addproducttocart/details/53/2")
                            .then()
                            .log().all()
                            .statusCode(200)
                            .extract().as(WishList.class);
            assertEquals(expectedUpdatetopwishlist, wishList.getUpdatetopwishlistsectionhtml());
            assertEquals(expectedMessage, wishList.getMessage());
        });
    }

    @Test
    @Tags({@Tag("API"), @Tag("UI")})
    void checkUsersAddress() {
        String login = "test@qa.test";
        String password = "test@qa.test";
        SelenideElement address = $(".address-list");

        step("Get cookie and set it to browser by API", () -> {
            String authorizationCookie = given()
                    .spec(demoWebShopRequest)
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .when()
                    .post("login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract()
                    .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });

        step("Open user's address", () ->
                open("customer/addresses"));

        step("Check the address's title", () ->
                address.$(".title").shouldHave(text("Anastasia Tester")));

        step("Check the user's name", () ->
                address.$(".name").shouldHave(text("Anastasia Tester")));

        step("Check the email", () ->
                address.$(".email").shouldHave(text("Email: " + login)));

        step("Check the phone number", () ->
                address.$(".phone").shouldHave(text("Phone number: +7987654321")));

        step("Check the address", () ->
                address.$(".address1").shouldHave(text("Address")));

        step("Check the city, state, zip code", () ->
                address.$(".city-state-zip").shouldHave(text("city, AA (Armed Forces Americas) zip")));

        step("Check the country", () ->
                address.$(".country").shouldHave(text("United States")));
    }
}
