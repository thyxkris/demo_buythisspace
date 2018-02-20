package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class ContactPageStepsDef extends BaseStepsDef {


    public ContactPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {

    }



    @Then("^I should be on the contact page$")
    public void iShouldBeOnTheContactPage() throws Throwable {
        IShouldBeOnThePage();
    }
}
