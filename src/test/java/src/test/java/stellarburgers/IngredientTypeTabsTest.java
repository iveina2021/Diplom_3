package src.test.java.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Test;
import src.test.java.stellarburgers.page.StellarburgersHomePage;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.open;
import static src.test.java.stellarburgers.helper.TestUtils.HOME_PAGE_URL;

public class IngredientTypeTabsTest {

    @Test
    public void tabFromBunToSauce() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);
        SelenideElement sauceTab = stellarburgersHomePage.selectSauceTab();
        assertTabIsSelected(sauceTab);
    }

    @Test
    public void tabFromSauceToFillings() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);
        SelenideElement fillingsTab = stellarburgersHomePage.selectFillingsTab();
        assertTabIsSelected(fillingsTab);
    }

    @Test
    public void tabFromFillingsToBun() {
        StellarburgersHomePage stellarburgersHomePage = open(HOME_PAGE_URL, StellarburgersHomePage.class);
        SelenideElement sauceTab = stellarburgersHomePage.selectSauceTab();
        SelenideElement bunTab = stellarburgersHomePage.selectBunTab();

        assertTabIsSelected(bunTab);
        assertTabIsNotSelected(sauceTab);
    }

    private void assertTabIsSelected(SelenideElement tab) {
        tab.shouldHave(cssClass("tab_tab_type_current__2BEPc"));
    }

    private void assertTabIsNotSelected(SelenideElement tab) {
        tab.shouldNotHave(cssClass("tab_tab_type_current__2BEPc"));
    }

    @After
    public void closeBrowser() {
        Selenide.closeWindow();
    }
}
