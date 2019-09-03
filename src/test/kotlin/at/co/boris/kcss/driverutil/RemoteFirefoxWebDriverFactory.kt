package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI

class RemoteFirefoxWebDriverFactory : RemoteWebDriverFactory() {
    override fun createDriver(): WebDriver {

        caps.browserName = "firefox"

        val options = FirefoxOptions()
        options.merge(caps)

        webDriver = RemoteWebDriver(URI.create("${getRemoteTestingServer()}/wd/hub").toURL(), options)
        webDriver.manage().window().maximize()

        return webDriver
    }

}
