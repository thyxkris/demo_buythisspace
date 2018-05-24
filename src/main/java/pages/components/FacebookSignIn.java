package pages.components;

import infrastructure.KWebDriver;
import infrastructure.KWebElement;
import libraries.helpers.ConfigHelper;
import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;
import pages.BaseModel;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 7/05/2018.
 */
public class FacebookSignIn extends BaseModel {
    public FacebookSignIn(ScenarioContext scenarioContext) {
        super(scenarioContext, "");
//        driver.waitForElementClickable(LOGIN);
    }

    By EMAIL = By.cssSelector("#email");
    By PASS = By.cssSelector("#pass");
    By LOGIN = By.name("login");
    public void SignInWithDefaultUserPass() throws Exception {
        logger.info(" SignInWithDefaultUserPass");
        Set<String> windowHandles = driver.getWindowHandles();
        KWebElement kWebElement_email = driver.findKElement(EMAIL);
        KWebElement kWebElement_pass = driver.findKElement(PASS);
        KWebElement kWebElement_login = driver.findKElement(LOGIN);

        kWebElement_email.sendKeys(ConfigHelper.getDefaultUserName());
        kWebElement_pass.sendKeys(ConfigHelper.getDefaultPass());
        driver.clickButton(kWebElement_login);


    }


}
