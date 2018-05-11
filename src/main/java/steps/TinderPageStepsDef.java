package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.helpers.ConfigHelper;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class TinderPageStepsDef extends BaseStepsDef {


    public TinderPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
        setResolution();
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {

    }

    @Then("^I should be on the tinder page$")
    public void iShouldBeOnTheWhyOutdoorPage() throws Throwable {
        IShouldBeOnThePage();
    }

    @Then("^I click like button on tinder$")
    public void iClickLikeButtonOnTinder() throws Throwable {


        int count = 0;
        int count_target = ConfigHelper.getTargetNumber();
        while (count < count_target) {

            tinderPageModel.clikeLikeButton();
            count++;
            scenarioContext.getScenario().write(count + " has been liked");
            logger.info(count + " has been liked");

        }

    }
}
