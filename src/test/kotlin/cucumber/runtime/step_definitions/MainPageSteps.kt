package cucumber.runtime.step_definitions

import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.en.Then


class MainPageSteps() : AbstractStepDefs() {

    @Before
    fun beforeScrenario(scenario: Scenario) {
        //FIXME testid cann be extract from scenario
        testId = extractTestIdFromScenarioName(scenario.name)
        current_scenario = scenario;
    }


    @Then("the peso-logo should be displayed")
    fun the_pesologo_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw cucumber.api.PendingException();
    }

}