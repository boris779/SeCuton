package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI

class RemoteFirefoxWebDriverFactory : WebDriverFactory() {
    override fun createDriver(): WebDriver {
        val capabilities = DesiredCapabilities()
        capabilities.browserName = "firefox"
        capabilities.setCapability("enableVNC", true)
        capabilities.setCapability("enableVideo", false)
        //capabilities.setCapability("screenResolution", screenSize)
        capabilities.setCapability("sessionTimeout", "5m")

        val executionTag = System.getProperty("executionTag", "executionTag_not_set")
        capabilities.setCapability("name", executionTag)

        val options = FirefoxOptions()
        options.merge(capabilities)

        val remoteTestingServer= System.getProperty("selenium.grid", "http://localhost:4444")


        webDriver = RemoteWebDriver(URI.create("$remoteTestingServer/wd/hub").toURL(), options)
        webDriver.manage().window().maximize()

        return webDriver
    }

}
