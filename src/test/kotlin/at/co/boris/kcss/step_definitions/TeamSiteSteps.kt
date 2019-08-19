package at.co.boris.kcss.step_definitions

import assertk.assertThat
import assertk.assertions.contains
import at.co.boris.kcss.pageobjects.TeamPage
import cucumber.api.java.en.Then


class TeamSiteSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {


    @Then("should {string} be part of the Core team")
    fun should_be_part_of_the_Core_team(name: String) {
        assertThat(getPage(TeamPage::class).getTeamMembers(), "Searching for $name in Core Team list").contains(name)
    }

}