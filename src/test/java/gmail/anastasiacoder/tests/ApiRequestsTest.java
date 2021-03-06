package gmail.anastasiacoder.tests;


import gmail.anastasiacoder.annotations.JiraIssue;
import gmail.anastasiacoder.annotations.JiraIssues;
import gmail.anastasiacoder.annotations.Layer;
import gmail.anastasiacoder.annotations.Microservice;
import gmail.anastasiacoder.models.reqres.Registration;
import gmail.anastasiacoder.models.reqres.RegistrationData;
import gmail.anastasiacoder.models.reqres.Users;
import gmail.anastasiacoder.models.reqres.single_resource.SingleResource;
import gmail.anastasiacoder.test_base.ApiRequestsBase;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Layer("REST")
@Owner("Ambidre")
@Story("ReqresIn")
@Feature("reqres.in")
@JiraIssues({@JiraIssue("HOMEWORK-335")})
@Tags({@Tag("API"),@Tag("TEST")})
public class ApiRequestsTest extends ApiRequestsBase {

    private RegistrationData registrationData;

    @BeforeAll
    static void prepare() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @DisplayName("Успешная регистрация")
    @Microservice("Registration")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void registerSuccessful() {
        registrationData = new RegistrationData();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("pistol");

        String expectedToken = "QpwL5tke4Pnpja7X4";
        int expectedId = 4;

        step("Check API register successful", () -> {
            Registration registration =
                    given()
                            .spec(reqresRequest)
                            .body(registrationData)
                            .when()
                            .post("/register")
                            .then()
                            .spec(successResponseSpec)
                            .extract().as(Registration.class);

            assertEquals(expectedToken, registration.getToken());
            assertEquals(expectedId, registration.getId());
        });
    }

    @DisplayName("Не успешная регистрация")
    @Microservice("Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void registerUnsuccessful() {
        registrationData = new RegistrationData();
        registrationData.setEmail("sydney@fife");

        String expectedError = "Missing password";
        step("Check API register unsuccessful", () -> {
            Registration registration =
                    given()
                            .spec(reqresRequest)
                            .body(registrationData)
                            .when()
                            .post("/register")
                            .then()
                            .statusCode(400)
                            .extract().as(Registration.class);
            assertEquals(expectedError, registration.getError());
        });
    }

    @DisplayName("Создание юзера")
    @Microservice("Users")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void createUser() {
        Users existUser = new Users();
        existUser.setName("morpheus");
        existUser.setJob("leader");

        String expectedName = "morpheus";
        String expectedJob = "leader";

        step("Check API create user", () -> {
            Users user =
                    given()
                            .spec(reqresRequest)
                            .body(existUser)
                            .when()
                            .post("/users")
                            .then()
                            .statusCode(201)
                            .extract().as(Users.class);

            assertEquals(expectedName, user.getName());
            assertEquals(expectedJob, user.getJob());
            assertNotNull(user.getId());
            assertNotNull(user.getCreatedAt());
        });
    }

    @DisplayName("Юзер не найден")
    @Microservice("Users")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void singleUserNotFound() {
        step("Check API user isn't found", () -> {
            given()
                    .spec(reqresRequest)
                    .when()
                    .get("/users/23")
                    .then()
                    .statusCode(404)
                    .body(is("{}"));
        });
    }

    @DisplayName("Проверка информации по юзеру")
    @Microservice("Users")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void singleResource() {
        int expectedId = 2;
        String expectedName = "fuchsia rose";
        int expectedYear = 2001;
        String expectedColor = "#C74375";
        String expectedPantoneValue = "17-2031";
        String expectedUrl = "https://reqres.in/#support-heading";
        String expectedText = "To keep ReqRes free, contributions towards server costs are appreciated!";

        step("Check API single resource", () -> {
            SingleResource singleResource =
                    given()
                            .spec(reqresRequest)
                            .when()
                            .get("/unknown/2")
                            .then()
                            .spec(successResponseSpec)
                            .extract().as(SingleResource.class);

            assertEquals(expectedId, singleResource.getData().getId());
            assertEquals(expectedName, singleResource.getData().getName());
            assertEquals(expectedYear, singleResource.getData().getYear());
            assertEquals(expectedColor, singleResource.getData().getColor());
            assertEquals(expectedPantoneValue, singleResource.getData().getPantoneValue());
            assertEquals(expectedUrl, singleResource.getSupport().getUrl());
            assertEquals(expectedText, singleResource.getSupport().getText());
        });
    }

    @DisplayName("Проверка наличия имени в списке")
    @Microservice("Users")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void checkNameInListResource() {
        step("Check API name = ~/ru/ in LIST <RESOURCE>", () -> {
            given()
                    .spec(reqresRequest)
                    .when()
                    .log().all()
                    .get("/unknown")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("data.findAll{it.name =~/ru/}.name.flatten()",
                            hasItems("cerulean", "true red"));
        });
    }
}
