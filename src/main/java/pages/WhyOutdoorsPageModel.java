package pages;

import libraries.infrastructure.ScenarioContext;
import org.openqa.selenium.By;

/**
 * Created by makri on 4/09/2017.
 */
public class WhyOutdoorsPageModel extends BasePageModel {
    public WhyOutdoorsPageModel(ScenarioContext scenarioContext) throws Exception {
        super(scenarioContext, "Why outdoor? - BuyThisSpace");
        //Why outdoor? - BuyThisSpace
    }

    private final By LCT_SEARCH= By.cssSelector("#lst-ib");
    private final By LCT_SEARCHBTN = By.name("btnk");

    public void eidtSearchText(String input) throws InterruptedException {
        driver.clickButton(LCT_SEARCH);

    }

    public void clickSearchButton() throws InterruptedException {
        driver.clickButton(LCT_SEARCHBTN);
    }
}
