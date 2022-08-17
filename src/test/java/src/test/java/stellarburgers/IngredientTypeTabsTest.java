package src.test.java.stellarburgers;

import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Test;
import src.test.java.stellarburgers.page.StellarburgersHomePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static src.test.java.stellarburgers.helper.TestUtils.HOME_PAGE_URL;

public class IngredientTypeTabsTest {

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
