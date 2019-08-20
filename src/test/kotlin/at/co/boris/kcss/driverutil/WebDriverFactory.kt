package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver


abstract class WebDriverFactory {

    lateinit var webDriver: WebDriver
    abstract fun createDriver(): WebDriver


    fun getDriverVersion(): String? {
        return System.getProperty("driver.version")
    }

    fun getBrowserVersion(): String? {
        return System.getProperty("browser.version")
    }


}