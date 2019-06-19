package cucumber.runtime.step_definitions

import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.de.Angenommen
import cucumber.api.java.de.Wenn
import cucumber.api.java.en.Given
import pageobjects.MainPage
import pageobjects.PageUrls


class NavigationSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    @Given("the Startpage is loaded")
    fun the_Startpage_is_loaded() {
        getWebDriverSession().gotoUrl(PageUrls.HOME, MainPage::class, testDataContainer)
    }


}
