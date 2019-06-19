package pageobjects

import driverutil.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory
import java.util.concurrent.TimeUnit


open class AbstractPage(driver: WebDriver) {

    protected var webDriver: WebDriver
        //get() = session.webDriver
    private var initialized = false
    protected lateinit var baseUrl: String

    init {
        if (initialized) {
            this.webDriver = DriverFactory.createWebDriver("dummy")
            this.webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS)
            PageFactory.initElements(webDriver, this)

            if (System.getProperty("baseUrl").isNotBlank()) {
                baseUrl = System.getProperty("baseUrl")
            }

            initialized = true
        } else {
            this.webDriver = driver
            //FIXME get the baseUrl from the WebDriverSession
            if (System.getProperty("baseUrl").isNotBlank()) {
                baseUrl = System.getProperty("baseUrl")
            }
        }
    }

}