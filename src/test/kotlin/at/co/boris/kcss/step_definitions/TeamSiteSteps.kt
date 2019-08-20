package at.co.boris.kcss.step_definitions

import assertk.assertThat
import assertk.assertions.contains
import at.co.boris.kcss.pageobjects.TeamPage
import io.cucumber.java.en.Then


class TeamSiteSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    init {
        Then("should {string} be part of the Core team") { name: String ->
            assertThat(getPage(TeamPage::class).getTeamMembers(), "Searching for $name in Core Team list").contains(name)
        }
    }

}