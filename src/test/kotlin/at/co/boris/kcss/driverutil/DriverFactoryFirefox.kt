package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class DriverFactoryFirefox : DriverFactory() {

   lateinit var webDriver: WebDriver

    override fun createDriver(): WebDriver {

        val driverVersion = System.getProperty("driver.version")
        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))

        WebDriverManager.firefoxdriver().version(driverVersion).setup()
        val options = FirefoxOptions()


        webDriver = FirefoxDriver(options)
        webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)

        return webDriver
    }

}