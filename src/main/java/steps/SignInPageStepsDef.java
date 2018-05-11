package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import libraries.infrastructure.ScenarioContext;

/**
 * Created by makri on 4/09/2017.
 */
public class SignInPageStepsDef extends BaseStepsDef {


    public SignInPageStepsDef(ScenarioContext scenarioContext) throws Throwable {
        super(scenarioContext);
    }

    @Override
    public void IShouldBeOnThePage() throws Throwable {

    }





    @Then("^I should be on the sign in page$")
    public void iShouldBeOnTheSearchPage() throws Throwable {
        IShouldBeOnThePage();
    }

    @Given("^I sign in with facebook account$")
    public void iSignInWithFacebookAccount() throws Throwable {

        signInPageModel.useFacebookSignInButton();
    }
}
