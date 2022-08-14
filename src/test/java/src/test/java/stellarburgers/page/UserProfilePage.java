package src.test.java.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class UserProfilePage {

    @FindBy(how = How.CSS, using = "button.Account_button__14Yp3")
    private SelenideElement signOutButton;

    public LoginPage signOutFromUserProfileByButton() {
        signOutButton.click();
        return page(LoginPage.class);
    }

}
