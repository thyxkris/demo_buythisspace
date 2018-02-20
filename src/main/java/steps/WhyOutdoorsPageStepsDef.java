package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class WhyOutdoorsPageStepsDef extends BaseStepsDef {


    public WhyOutdoorsPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {

    }



    @When("^I input \"([^\"]*)\" in the search edit box$")
    public void iInputInTheSearchEditBox(String arg0) throws Throwable {

        searchPageModel.eidtSearchText(arg0);
    }

    @And("^click search button$")
    public void clickSearchButton() throws Throwable {

        searchPageModel.clickSearchButton();
    }



    @Then("^I should be on the why outdoor page$")
    public void iShouldBeOnTheWhyOutdoorPage() throws Throwable {
        IShouldBeOnThePage();
    }
}
