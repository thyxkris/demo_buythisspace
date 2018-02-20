package pages;

import helpers.ConfigHelper;
import infrastructure.KWebDriver;
import libraries.infrastructure.ScenarioContext;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pages.components.HeaderBar;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by makri on 17/05/2017.
 */
public class BasePageModel extends BaseModel {

    protected static Logger logger;
    protected KWebDriver driver;
    protected HeaderBar headerBar;

    public BasePageModel(ScenarioContext scenarioContext, String title) throws Exception {
        super(scenarioContext, title, null);

        setup(scenarioContext);

    }

    public BasePageModel(ScenarioContext scenarioContext, String title, String url) throws Exception {
        super(scenarioContext, title, url);
        setup(scenarioContext);
    }


    public void setup(ScenarioContext scenarioContext) throws Exception {

        waitForPageLoad();
        this.scenarioContext = scenarioContext;
        this.driver = scenarioContext.getWebDriver();
        //components initilizations
        this.headerBar = new HeaderBar(scenarioContext);
        if (logger == null) {
            logger = scenarioContext.getLogger();
        }
        logger.info(this.getClass() + " finish to initialize  after super's setup");

    }

    public void goToNextPageByLinkText(String linkText) throws Exception {
        this.headerBar.goToNextPageByLinkText(linkText);
    }


}
