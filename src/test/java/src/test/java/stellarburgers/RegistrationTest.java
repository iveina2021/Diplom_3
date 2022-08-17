package src.test.java.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import src.test.java.stellarburgers.helper.api.UserRequestTestHelper;
import src.test.java.stellarburgers.page.LoginPage;
import src.test.java.stellarburgers.page.RegisterPage;
import src.test.java.stellarburgers.page.StellarburgersHomePage;

import static com.codeborne.selenide.Selenide.open;
import static src.test.java.stellarburgers.helper.TestUtils.*;

public class RegistrationTest {

    @Test
    public void successfulRegister() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        RegisterPage registerPage = loginPage.clickOnRegisterButton();

        registerPage.fillRegisterFormAndClick(NAME, EMAIL, PASSWORD)
                .getLoginHeader().shouldHave(Condition.text("Вход"));
    }

    @Test
    public void unsuccessfulRegisterWithIncorrectPassword() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        RegisterPage registerPage = loginPage.clickOnRegisterButton();

        String shortPassword = "123";
        registerPage.fillRegisterFormAndClick(NAME, EMAIL, shortPassword)
                .getIncorrectPasswordError().shouldHave(Condition.text("Некорректный пароль"));
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
