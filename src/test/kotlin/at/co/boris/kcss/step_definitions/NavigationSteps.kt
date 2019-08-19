package at.co.boris.kcss.step_definitions

import at.co.boris.kcss.pageobjects.MainPage
import at.co.boris.kcss.pageobjects.PageUrls
import io.cucumber.java.en.Given
import io.cucumber.java.en.When


class NavigationSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    @Given("the startpage is loaded")
    fun the_Startpage_is_loaded() {
        getWebDriverSession().gotoUrl(PageUrls.HOME, MainPage::class, testDataContainer)
    }



    @When("the Teamsite is opened")
    fun teamsite_open() {
        getPage(MainPage::class).clickMenuEntry("Team")
    }

}
