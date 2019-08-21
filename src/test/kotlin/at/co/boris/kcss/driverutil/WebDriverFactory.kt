package at.co.boris.kcss.driverutil

import logger
import org.openqa.selenium.WebDriver


abstract class WebDriverFactory {

    lateinit var webDriver: WebDriver
    abstract fun createDriver(): WebDriver
    protected val log by logger()


    fun getDriverVersion(): String? {
        return System.getProperty("driver.version")
    }

    fun getBrowserVersion(): String? {
        return System.getProperty("browser.version")
    }


}