package pageobjects

import driverutil.DriverFactory
import driverutil.WebDriverSession
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory
import java.util.concurrent.TimeUnit


open class AbstractPage(val session: WebDriverSession) {

    protected val webDriver: WebDriver
        get() = session.webDriver


    init {
        session.activate(this)
    }


}