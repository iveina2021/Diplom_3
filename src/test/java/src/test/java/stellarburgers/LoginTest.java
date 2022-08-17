package src.test.java.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.test.java.stellarburgers.helper.api.UserRequestTestHelper;
import src.test.java.stellarburgers.page.ForgotPasswordPage;
import src.test.java.stellarburgers.page.LoginPage;
import src.test.java.stellarburgers.page.RegisterPage;
import src.test.java.stellarburgers.page.StellarburgersHomePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static src.test.java.stellarburgers.helper.TestUtils.*;
import static src.test.java.stellarburgers.helper.api.UserRequestTestHelper.createUserRequest;

public class LoginTest {

    @Before
    public void setUp() throws Exception {
        Response createUserResponse = createUserRequest(prepareCreateUserRequest(EMAIL, PASSWORD, NAME));
        createUserResponse
                .then()
                .statusCode(200);
    }

    @Test
    public void loginFromSignInButton() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromSignInButton();

        loginPage.login(EMAIL, PASSWORD);

        $(byText("Соберите бургер")).shouldBe(visible);
    }

    @Test
    public void loginFromUserProfileButton() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();

        loginPage.login(EMAIL, PASSWORD);

        $(byText("Соберите бургер")).shouldBe(visible);
    }

    @Test
    public void loginFromRegisterPage() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        RegisterPage registerPage = loginPage.clickOnRegisterButton();
        registerPage.openLoginPageFromRegisterPageButton();

        loginPage.login(EMAIL, PASSWORD);

        $(byText("Соберите бургер")).shouldBe(visible);
    }

    @Test
    public void loginFromForgotPasswordPage() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromSignInButton();

        ForgotPasswordPage recoverPasswordPage = loginPage.clickOnRecoverPassword();

        recoverPasswordPage.openLoginFromForgotPasswordPageButton();

        loginPage.login(EMAIL, PASSWORD);

        $(byText("Соберите бургер")).shouldBe(visible);
    }

    @After
    public void deleteUser() {
        Response response = UserRequestTestHelper.loginUserRequest(prepareEmailPasswordRequest(EMAIL, PASSWORD));
        if (response.getStatusCode() == 200) {
            String accessToken = response
                    .getBody().jsonPath().getString("accessToken");

            UserRequestTestHelper.deleteUser(accessToken);
        }
    }

    @After
    public void closeBrowser() {
        Selenide.closeWindow();
    }

}
