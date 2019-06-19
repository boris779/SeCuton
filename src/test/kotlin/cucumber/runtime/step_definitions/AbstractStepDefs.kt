package cucumber.runtime.step_definitions

import assertk.fail
import cucumber.api.Scenario
import cucumber.api.java8.De
import cucumber.api.java8.En
import driverutil.PageNotFoundException
import driverutil.WebDriverSession
import driverutil.WebDriverSessionStore
import driverutil.toBoolean
import org.openqa.selenium.WebDriver
import pageobjects.AbstractPage
import kotlin.reflect.KClass

open class AbstractStepDefs : En {

    val log by logger()
    var header: Boolean = false
    var current_scenario : Scenario? = null

    fun getWebDriverSession(): WebDriverSession {
        return WebDriverSessionStore.get(testId)
    }

     fun getWebDriver(): WebDriver {
        return getWebDriverSession().webDriver
    }

    fun <T : AbstractPage> getPage(pageClass: KClass<T>): T {
        val page = getWebDriverSession().currentPage

        if (pageClass.isInstance(page)) {
            return page as T
        }
        log.error("Expect Page from type $pageClass but was $page")
        throw PageNotFoundException("Expect Page from type $pageClass but was $page")
    }

    fun getCurrentPage(): AbstractPage? {
        return getWebDriverSession().currentPage
    }

    fun setCurrentPage(page: AbstractPage) {
        getWebDriverSession().currentPage = page
    }
}

 fun extractTestIdFromScenarioName(scenarioName: String): String {
     val regex = "^\\[(.*) \\[.*\$".toRegex()
     try {
         return regex.find(scenarioName)!!.groups.get(1)!!.value
     } catch (e: NullPointerException) {
         fail("Scenarioname is not correct formated $scenarioName. Pattern: '[XXX-99 [Filename]")
     }
     return (scenarioName)
 }

