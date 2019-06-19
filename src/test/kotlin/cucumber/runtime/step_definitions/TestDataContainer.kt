package cucumber.runtime.step_definitions

import cucumber.api.Scenario

class TestDataContainer {
    private val testDataMap: MutableMap<String, Any> = mutableMapOf()

    init {
        testDataMap.put("testId", "init")
    }

    fun getScenario() = testDataMap.get("scenario") as Scenario
    fun getTestId() = testDataMap.get("testId") as String
    fun getScenarioTags() = getScenario().sourceTagNames

    fun setScenario(scenario: Scenario) {
        testDataMap.put("scenario", scenario)
        testDataMap.put("testId", extractTestIdFromScenarioName(scenario.name))
    }

    fun setTestData(key: String, value: Any) {
        testDataMap.put(key, value)
    }

    fun getBrowser(): String {
        return testDataMap.get("browser") as String
    }

    fun getBrowserType(): String {
        return testDataMap.get("browser.type") as String
    }

    fun getBrowserVersion(): String {
        return testDataMap.get("browser.version") as String
    }



    fun isMobile(): Boolean {
        if (getBrowserVersion().contains("mobile")) {
            return true
        }
        if (getBrowser().contains("mobile")) {
            return true
        }

        if (getBrowserType().contains("mobile")) {
            return true
        }

        if (testDataMap.get("mobileEmulation") as Boolean) {
            return true
        }

        return false
    }

    fun needsInitializing(): Boolean {
        if (testDataMap.get("initialized") as Boolean) {
            return false
        }
        return true
    }

}