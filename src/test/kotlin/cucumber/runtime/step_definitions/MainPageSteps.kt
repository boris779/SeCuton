package cucumber.runtime.step_definitions

import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.en.Then
import pageobjects.MainPage


class MainPageSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {


    @Then("the peso-logo should be displayed")
    fun the_pesologo_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw cucumber.api.PendingException();
    }


}