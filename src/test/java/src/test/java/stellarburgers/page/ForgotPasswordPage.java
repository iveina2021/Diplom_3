package src.test.java.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {

    // вход через кнопку в форме восстановления пароля
    @FindBy(how = How.CSS, using = "a[href='/login']")
    private SelenideElement loginFromForgotPasswordPageButton;

    public LoginPage openLoginFromForgotPasswordPageButton() {
        loginFromForgotPasswordPageButton.click();
        return page(LoginPage.class);
    }
}
