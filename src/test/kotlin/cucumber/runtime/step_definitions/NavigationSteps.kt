package cucumber.runtime.step_definitions

import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.de.Angenommen
import cucumber.api.java.de.Wenn
import cucumber.api.java.en.Given
import pageobjects.MainPage
import pageobjects.PageUrls


class NavigationSteps : AbstractStepDefs() {

    @Before
    fun beforeScrenario(scenario: Scenario) {
        //FIXME testid cann be extract from scenario
        testId = extractTestIdFromScenarioName(scenario.name)
        current_scenario = scenario;
    }

    @Angenommen("die Startseite ist geladen")
    @Wenn("die Startseite geladen ist")
    fun dieStartseiteGeladenIst() {
        //FIXME
    }

    @Given("the Startpage is loaded")
    fun the_Startpage_is_loaded() {
        getWebDriverSession().gotoUrl(PageUrls.HOME, MainPage::class, this.current_scenario!!)
    }


}
