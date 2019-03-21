package cucumber.runtime.step_definitions

import cucumber.api.java.en.Then
import cucumber.api.java.en.When


class TeamSiteSteps : AbstractStepDefs() {


    @When("the Teamsite is loaded")
    fun the_Teamsite_is_loaded() {
        // Write code here that turns the phrase above into concrete actions
        throw cucumber.api.PendingException();
    }


    @Then("should be {string} part of the Core team")
    fun should_be_part_of_the_Core_team(name: String) {
        // Write code here that turns the phrase above into concrete actions
        throw cucumber.api.PendingException()
        ;
    }

}