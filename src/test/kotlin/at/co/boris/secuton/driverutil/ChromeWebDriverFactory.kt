package at.co.boris.secuton.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class ChromeWebDriverFactory : WebDriverFactory() {

    override fun createDriver(): WebDriver {

        WebDriverManager.chromedriver().version(getDriverVersion()).setup()
        val options = ChromeOptions()

        webDriver = ChromeDriver(options)
        webDriver.manage().window().size = screenDimension.dimension

        return webDriver
    }


}