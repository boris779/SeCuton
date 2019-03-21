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
    private var initialized = false
    protected lateinit var baseUrl: String

    init {
        if (initialized) {
            this.webDriver = DriverFactory.browser
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

/*
    protected fun fillInputfieldWithId(domId: String, value: String) {
        var element: WebElement = webDriver.findElement(By.cssSelector("label[id=" + domId + "-label-id]"))

        element.click()
        element = webDriver.findElement(By.cssSelector("input[id=" + domId + "]"))
        element.sendKeys(value)
        element.sendKeys(Keys.TAB)
    }

    protected fun selectCheckbox(domId: String, value: Boolean) {
        val element: WebElement = webDriver.findElement(By.id(domId))
        if (value != webDriver.findElement(By.id(domId)).isSelected()) {
            val actions = Actions(webDriver);

            actions.moveToElement(webDriver.findElement(By.id(domId))).click().perform();
        }
        element.sendKeys(Keys.TAB)
    }


    */
}