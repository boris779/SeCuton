package at.co.boris.kcss.step_definitions

import cucumber.api.Scenario

class TestDataContainer {
    private val testDataMap: MutableMap<String, Any> = mutableMapOf()

    init {
        testDataMap["testId"] = "init"
    }

    fun getScenario() = testDataMap["scenario"] as Scenario
    fun getTestId() = testDataMap["testId"] as String
    fun getScenarioTags() = getScenario().sourceTagNames

    fun setScenario(scenario: Scenario) {
        testDataMap["scenario"] = scenario
        testDataMap["testId"] = extractTestIdFromScenarioName(scenario.name)
    }

    fun setTestData(key: String, value: Any) {
        testDataMap[key] = value
    }

    private fun getBrowser(): String {
        return testDataMap["browser"] as String
    }

    private fun getBrowserType(): String {
        return testDataMap["browser.type"] as String
    }

    private fun getBrowserVersion(): String {
        return testDataMap["browser.version"] as String
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

        if (testDataMap["mobileEmulation"] as Boolean) {
            return true
        }

        return false
    }

    fun needsInitializing(): Boolean = (testDataMap["initialized"] as Boolean)

    fun isLocalRun(): Boolean {
        return (testDataMap["localRun"] as Boolean)
    }

}