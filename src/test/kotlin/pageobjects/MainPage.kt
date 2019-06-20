package pageobjects

import driverutil.WebDriverSession
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

open class MainPage(session: WebDriverSession) : AbstractPage(session) {
    fun getLogo(): MutableList<WebElement>? {
        return webDriver.findElements(By.cssSelector("div.navbar-header a.navbar-brand img"))
    }

    fun clickMenuEntry(menustring: String): MainPage {
        val link = webDriver.findElement(By.linkText(menustring))

        link.click()


        return when(menustring.toLowerCase()) {
            "team" -> TeamPage(session)
            "contact" -> ContactPage(session)
            else -> this
        }

    }


}
