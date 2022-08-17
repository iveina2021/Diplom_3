package src.test.java.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StellarburgersHomePage {

    private final SelenideElement bunTab = $$(".tab_tab__1SPyG").filterBy(text("Булки")).first();
    private final SelenideElement sauceTab = $$(".tab_tab__1SPyG").filterBy(text("Соусы")).first();
    private final SelenideElement fillingsTab = $$(".tab_tab__1SPyG").filterBy(text("Начинки")).first();

    private final SelenideElement createBurgerHeader = $(".BurgerIngredients_ingredients__1N8v2").find(byText("Соберите бургер"));

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement signInButton;

    @FindBy(how = How.CSS, using = "a[href='/account']")
    private SelenideElement userProfileButton;

    public LoginPage openLoginPageFromSignInButton() {
        signInButton.click();
        return page(LoginPage.class);
    }

    public LoginPage openLoginPageFromUserProfileButton() {
        userProfileButton.click();
        return page(LoginPage.class);
    }

    public UserProfilePage openUserProfilePage() {
        userProfileButton.click();
        return page(UserProfilePage.class);
    }

    public SelenideElement selectBunTab() {
        bunTab.click();
        return bunTab;
    }

    public SelenideElement selectSauceTab() {
        sauceTab.click();
        return sauceTab;
    }

    public SelenideElement selectFillingsTab() {
        fillingsTab.click();
        return fillingsTab;
    }

    public SelenideElement getCreateBurgerHeader() {
        return createBurgerHeader;
    }
}



