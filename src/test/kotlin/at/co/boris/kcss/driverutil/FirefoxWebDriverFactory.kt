package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class FirefoxWebDriverFactory : WebDriverFactory() {



//        val driverVersion =
//        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))


    override fun createDriver(): WebDriver {

        WebDriverManager.firefoxdriver().version(getDriverVersion()).setup()

        webDriver = FirefoxDriver(FirefoxOptions())
        //webDriver.manage().window().size = Dimension(getScreenResolution().width, getScreenResolution().height)

        return webDriver
    }

}