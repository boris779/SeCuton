package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions

class EdgeWebDriverFactory : WebDriverFactory(){
    override fun createDriver(): WebDriver {

        val driverVersion = System.getProperty("driver.version")
        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))

        WebDriverManager.edgedriver().version(driverVersion).setup()
        val options = EdgeOptions()

        webDriver = EdgeDriver(options)
        webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)

        return webDriver
    }
}
