package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class FirefoxWebDriverFactory : WebDriverFactory() {


    override fun createDriver(): WebDriver {

        WebDriverManager.firefoxdriver().version(getDriverVersion()).setup()

        webDriver = FirefoxDriver(FirefoxOptions())
        webDriver.manage().window().size = screenDimension.dimension

        return webDriver
    }

}