package cucumber.runtime.step_definitions

import assertk.assertThat
import assertk.assertions.contains
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import pageobjects.TeamPage


class TeamSiteSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {


    @Then("should be {string} part of the Core team")
    fun should_be_part_of_the_Core_team(name: String) {

        assertThat(getPage(TeamPage::class).getTeamMembers(), "Searching for $name in Core Team list").contains(name)

    }

}