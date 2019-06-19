package cucumber.runtime.step_definitions

import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.Before
import driverutil.WebDriverSessionStore
import logger
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot


class Hooks(private val testDataContainer: TestDataContainer) {

    val log by logger()

    @Before
    fun beforeScrenario(scenario: Scenario) {


        testDataContainer.setScenario(scenario)
        testDataContainer.setTestData("browser.type", System.getProperty("browser", "no browser set"))
        testDataContainer.setTestData("browser.version", System.getProperty("browser.version", "no version set"))
        testDataContainer.setTestData("initialized", false)

        // to check if it runs on Jenkins or local
        val jobname = System.getenv("JOB_NAME")

        //Do Database resets here

        if (jobname != null) {
            log.debug("##################################################################")
            log.debug("###### JENKINS INFOS: ############################################")
            log.debug("# BUILD_NUMBER:" + System.getenv("BUILD_NUMBER"))
            log.debug("# JOB_NAME: " + System.getenv("JOB_NAME"))
            log.debug("# JENKINS_URL:" + System.getenv("JENKINS_URL"))
            log.debug("# WORKSPACE: " + System.getenv("WORKSPACE"))
            log.debug("# NODE_NAME: " + System.getenv("NODE_NAME"))
            log.debug("##################################################################")
        }

        log.debug("####################################")
        log.info("executing Scenario:" + scenario.name)
        log.debug("####################################")
    }


    @After
    fun afterScenario(scenario: Scenario) {
        val testId = extractTestIdFromScenarioName(scenario.name)
        val webDriverSession = WebDriverSessionStore.get(testId)

        try {
            if (scenario.isFailed) {
                if (webDriverSession.currentPage != null) {
                    scenario.embed((webDriverSession.webDriver as TakesScreenshot).getScreenshotAs(OutputType.BYTES), "image/png")
                }
            }
        } finally {
            WebDriverSessionStore.remove(testId)
        }
    }
}
