package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.opera.OperaOptions

class OperaWebDriverFactory : WebDriverFactory() {
    override fun createDriver(): WebDriver {

        val driverVersion = System.getProperty("driver.version")
        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))

        WebDriverManager.operadriver().version(driverVersion).setup()
        val options = OperaOptions()

        webDriver = OperaDriver(options)
        webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)

        return webDriver
    }

}
