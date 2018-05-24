package pages;

import infrastructure.KWebElement;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;
import pages.components.FacebookSignIn;

import java.util.List;
import java.util.Set;

/**
 * Created by makri on 4/09/2017.
 */
public class SignInPageModel extends BasePageModel {
    public SignInPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "Tinder | Swipe. Match. Chat.");
        waitForPageLoad();
        driver.waitForElementClickable(By.xpath("//span[text()='Log in with Facebook']"));

    }

    private final By BTN_FACEBOOK_SIGNIN = By.xpath("//span[text()='Log in with Facebook']");
    private final By BTN_Next = By.xpath("//span[text()='Next']");
    private final By BTN_ALLOW = By.cssSelector("button[class^='button']");
    private final By BTN_ENABLE = By.xpath("//span[text()='Enable']");

    public void useFacebookSignInButton() throws Exception {

        logger.info(" start to log in facebook");
        Set<String> wHandles = driver.getWindowHandles();
        String winHandleBefore = driver.getWindowHandle();

        driver.clickButton(BTN_FACEBOOK_SIGNIN);
        while (wHandles.size() == driver.getWindowHandles().size()) {
            Thread.sleep(1000);
        }
        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        FacebookSignIn facebookSignIn = new FacebookSignIn(scenarioContext);
        facebookSignIn.SignInWithDefaultUserPass();
        while (wHandles.size() != driver.getWindowHandles().size()) {
            Thread.sleep(1000);
        }

        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);

        if (driver.manage().window().getSize().getWidth() > 782) {
            driver.waitForElementClickable(BTN_Next);
            driver.clickButton(BTN_Next);

            Thread.sleep(1000);
            driver.clickButton(BTN_Next);

            driver.waitForElementInvisible(BTN_Next);
        }
        // driver.clickButton(BTN_ALLOW);
        clickAllowLocation();

        driver.clickButton(BTN_ENABLE);
        driver.waitForElementInvisible(BTN_ENABLE);

    }

    public void clickAllowLocation() {
        try {

            logger.info(" clickAllowLocation");
            List<KWebElement> webElementList = scenarioContext.getWebDriver().findKElements(By.cssSelector("button[class^='button']"));
            for (KWebElement kWebElement : webElementList) {
                if (kWebElement.getAttribute("data-testid") != null && kWebElement.getAttribute("data-testid").equalsIgnoreCase("allow")) {

                    driver.clickButton(kWebElement);

                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException se) {
            logger.info(se.getLocalizedMessage());
            takeScreenShot(true, false, null, 0, 0, 0.5, "");
            clickAllowLocation();
        }
    }
}
