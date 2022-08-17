package src.test.java.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    private final SelenideElement loginHeader = $(".Auth_login__3hAey").find(byText("Вход"));

    private final SelenideElement incorrectPasswordError = $(".Auth_login__3hAey").find(byText("Некорректный пароль"));

    @FindBy(how = How.CSS, using = "input[name='name']")
    private SelenideElement fieldEmail;

    @FindBy(how = How.CSS, using = "input[name='Пароль']")
    private SelenideElement fieldPassword;

    @FindBy(how = How.CSS, using = "form > button")
    private SelenideElement loginButton;

    // линка для перехода в конструктор
    @FindBy(linkText = "Конструктор")
    private SelenideElement constructorButton;
    // переход на главную кликом по лого

    @FindBy(how = How.CSS, using = ".AppHeader_header__logo__2D0X2 > a")
    private SelenideElement logo;

    @FindBy(linkText = "Зарегистрироваться")
    private SelenideElement registerLink;


    @FindBy(linkText = "Восстановить пароль")
    private SelenideElement recoverPasswordButton;

    public StellarburgersHomePage login(String email, String password) {
        fieldEmail.setValue(email);
        fieldPassword.setValue(password);
        loginButton.click();
        return page(StellarburgersHomePage.class);
    }

    public StellarburgersHomePage openMainPageFromConstructorButton() {
        constructorButton.click();
        return page(StellarburgersHomePage.class);
    }

    public StellarburgersHomePage openMainPageFromLogoClick() {
        logo.click();
        return page(StellarburgersHomePage.class);
    }

    public RegisterPage clickOnRegisterButton() {
        registerLink.click();
        return page(RegisterPage.class);
    }

    public ForgotPasswordPage clickOnRecoverPassword() {
        recoverPasswordButton.click();
        return page(ForgotPasswordPage.class);
    }

    public SelenideElement getLoginHeader() {
        return loginHeader;
    }

    public SelenideElement getIncorrectPasswordError() {
        return incorrectPasswordError;
    }

}

