package at.co.boris.secuton.step_definitions

import at.co.boris.secuton.pageobjects.MainPage
import at.co.boris.secuton.pageobjects.PageUrls


class NavigationSteps(testDataContainer: TestDataContainer) : AbstractStepDefs(testDataContainer) {

    init {
        Given("the startpage is loaded") {
            getWebDriverSession().gotoUrl(PageUrls.HOME, MainPage::class, testDataContainer)
        }

        When("the Teamsite is opened") {
            getPage(MainPage::class).clickMenuEntry("Team")
        }
    }


/*
    @Given("the startpage is loaded")

    fun the_Startpage_is_loaded() {
        getWebDriverSession().gotoUrl(PageUrls.HOME, MainPage::class, testDataContainer)
    }



    @When("the Teamsite is opened")
    fun teamsite_open() {
        getPage(MainPage::class).clickMenuEntry("Team")
    }
*/
}
