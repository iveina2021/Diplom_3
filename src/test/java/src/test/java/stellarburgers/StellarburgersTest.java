package src.test.java.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.test.java.stellarburgers.page.*;

import java.util.UUID;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StellarburgersTest {

    public static final String USERNAME = "Шелдон";
    public static final String EMAIL = "Jekins!!@mail.ru";
    public static final String PASSWORD = "123456";

    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
    }

    @Test
    public void successfulRegister() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        RegisterPage registerPage = loginPage.clickOnRegisterButton();

        // unique email because we can't delete user
        registerPage.fillRegisterFormAndClick(USERNAME, UUID.randomUUID() + EMAIL, PASSWORD);

        $(byText("Вход")).shouldBe(visible);
    }

    @Test
    public void unsuccessfulRegisterWithIncorrectPassword() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        RegisterPage registerPage = loginPage.clickOnRegisterButton();

        // unique email because we can't delete user
        String shortPassword = "123";
        registerPage.fillRegisterFormAndClick(USERNAME, UUID.randomUUID() + EMAIL, shortPassword);
        $(byText("Некорректный пароль")).shouldBe(visible);
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

    @Test
    public void openHomePageFromConstructorButton() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        loginPage.openMainPageFromConstructorButton();
        $(byText("Соберите бургер")).shouldBe(visible);
    }

    @Test
    public void openHomePageFromLogo() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromUserProfileButton();
        loginPage.openMainPageFromLogoClick();

        $(byText("Соберите бургер")).shouldBe(visible);
    }

    @Test
    public void signOutFromUserProfile() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);

        LoginPage loginPage = stellarburgersHomePage.openLoginPageFromSignInButton();

        loginPage.login(EMAIL, PASSWORD);

        UserProfilePage userProfilePage = stellarburgersHomePage.openUserProfilePage();

        userProfilePage.signOutFromUserProfileByButton();

        $(byText("Вход")).shouldBe(visible);
    }

    @Test
    public void tabFromBunToSauce() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);
        stellarburgersHomePage.sauceTabClick();
        $(byText("Соус Spicy-X")).shouldBe(visible);
    }

    @Test
    public void tabFromSauceToFillings() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);
        stellarburgersHomePage.fillingsTabClick();
        $(byText("Мясо бессмертных моллюсков Protostomia")).shouldBe(visible);
    }

    @Test
    public void tabFromFillingsToBun() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);
        stellarburgersHomePage.sauceTabClick();
        stellarburgersHomePage.bunTabClick();
        $(byText("Флюоресцентная булка R2-D3")).shouldBe(visible);
    }

    @After
    public void closeBrowser() {
        Selenide.closeWindow();
    }
}
