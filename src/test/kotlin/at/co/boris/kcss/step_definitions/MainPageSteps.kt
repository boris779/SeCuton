package at.co.boris.kcss.step_definitions

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import at.co.boris.kcss.pageobjects.MainPage
import io.cucumber.java.en.Then


class MainPageSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    @Then("the peso-logo should be displayed")
    fun the_pesologo_should_be_displayed() {

        val eleLogoList = getPage(MainPage::class).getLogo()

        assertThat(eleLogoList!!.size, "found Logos via css").isGreaterThan(0)
        assertThat(eleLogoList[0].getAttribute("alt"), "checked the alternative Text from Logo").isEqualTo("PESO - Logo")

    }
}