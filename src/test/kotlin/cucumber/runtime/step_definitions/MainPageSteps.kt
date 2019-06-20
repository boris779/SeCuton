package cucumber.runtime.step_definitions

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import assertk.assertions.support.expected
import assertk.assertions.support.fail
import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.en.Then
import pageobjects.MainPage


class MainPageSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {


    @Then("the peso-logo should be displayed")
    fun the_pesologo_should_be_displayed() {

        val ele_logo_list = getPage(MainPage::class).getLogo()

        assertThat(ele_logo_list!!.size, "found Logos via css").isGreaterThan(0)
        assertThat(ele_logo_list[0].getAttribute("alt"), "checked the alternative Text from Logo").isEqualTo("PESO - Logo")

    }


}