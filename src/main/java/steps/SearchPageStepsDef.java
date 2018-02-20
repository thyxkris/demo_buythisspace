package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class SearchPageStepsDef extends BaseStepsDef {


    public SearchPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {

    }





    @Then("^I should be on the search page$")
    public void iShouldBeOnTheSearchPage() throws Throwable {
        IShouldBeOnThePage();
    }
}
