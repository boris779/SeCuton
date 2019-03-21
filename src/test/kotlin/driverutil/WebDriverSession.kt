package driverutil

import assertk.fail
import cucumber.api.Scenario
import org.openqa.selenium.WebDriver
import pageobjects.AbstractPage
import pageobjects.PageUrls

class WebDriverSession(val testId: String) {

    var currentPage: AbstractPage? = null
    var lastPage: AbstractPage? = null
    val webDriver: WebDriver by lazy { DriverFactory.browser }
    val baseUrl: String by lazy {
        if (System.getProperty("baseUrl").isBlank()) {
            fail("No BaseUrl is defined, do not know where to run the tests. Use '-DbaseUrl' to add the url where testenvironment is running ")
        }
        System.getProperty("baseUrl")
    }

    fun close() {
        webDriver.quit()
        currentPage = null
    }

    //TODO Change from Class to KClass @Andreas
    fun setCurrentPageClass(currentPageClass: Class<out AbstractPage>) {
        lastPage = currentPage
        currentPage = currentPageClass.getDeclaredConstructor(WebDriver::class.java).newInstance(webDriver)
    }

    fun setCurrentPageFromPage(currentPage: AbstractPage) {
        setCurrentPageClass(currentPage.javaClass)
    }

    private fun getDomainFromBaseUrl(): String {
        return "^http[s]?:\\/\\/([^:\\/]*)".toRegex().find(baseUrl)!!.groups.get(1)!!.value
    }


    fun gotoUrl(subUrl: PageUrls, pageClass: Class<out AbstractPage>, scenario: Scenario) {

        val fullUrl = baseUrl + subUrl.subUrl
        webDriver.get(fullUrl)
        scenario.write("URL used: " + fullUrl)
        setCurrentPageClass(pageClass)
    }

}