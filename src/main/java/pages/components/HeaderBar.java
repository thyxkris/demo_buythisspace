package pages.components;

import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;
import pages.BaseModel;

/**
 * Created by Admin on 19/02/2018.
 */
public class HeaderBar extends BaseModel {
    public HeaderBar(ScenarioContext scenarioContext) {
        super(scenarioContext, "");
    }
    public void goToNextPageByLinkText(String linkText) throws Exception {
        scenarioContext.getCurrentPage().clickButtonAndContinue(By.partialLinkText(linkText));
    }


}
