package src.test.java.stellarburgers.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class StellarburgersHomePage {

    private final SelenideElement bunTab = $(".BurgerIngredients_ingredients__1N8v2").find(byText("Булки"));
    private final SelenideElement sauceTab = $(".BurgerIngredients_ingredients__1N8v2").find(byText("Соусы"));
    private final SelenideElement fillingsTab = $(".BurgerIngredients_ingredients__1N8v2").find(byText("Начинки"));

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

    public void bunTabClick() {
        bunTab.click();
    }

    public void sauceTabClick() {
        sauceTab.click();
    }

    public void fillingsTabClick() {
        fillingsTab.click();
    }

    public SelenideElement getCreateBurgerHeader() {
        return createBurgerHeader;
    }
}



