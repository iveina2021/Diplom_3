package src.test.java.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    @FindBy(how = How.CSS, using = "input[name='name']")
    private SelenideElement registerFieldName;

    @FindBy(how = How.CSS, using = "input[type='password']")
    private SelenideElement registerFieldPassword;

    // линка для регистрации
    @FindBy(how = How.CSS, using = "a[href='/register']")
    private SelenideElement registerLink;

    // кнопка "зарегистрироваться"
    @FindBy(how = How.CLASS_NAME, using = "button_button_type_primary__1O7Bx")
    private SelenideElement registerButton;

    //вход через кнопку в форме регистрации
    @FindBy(how = How.CSS, using = "a[href='/login']")
    private SelenideElement loginFromRegisterPageButton;

    public LoginPage fillRegisterFormAndClick(String name, String email, String password) {
        fillRegisterForm(name, email, password);
        registerButton.click();
        return page(LoginPage.class);
    }

    public LoginPage openLoginPageFromRegisterPageButton() {
        loginFromRegisterPageButton.click();
        return page(LoginPage.class);
    }

    private void fillRegisterForm(String name, String email, String password) {
        registerFieldName.setValue(name);
        findRegisterFieldEmail().setValue(email);
        registerFieldPassword.setValue(password);
    }

    private SelenideElement findRegisterFieldEmail() {
        return $(byText("Email")).parent().find(byCssSelector("input"));
    }
}
