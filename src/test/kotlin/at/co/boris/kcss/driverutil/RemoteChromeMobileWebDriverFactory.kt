package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI
import java.time.LocalDateTime

class RemoteChromeMobileWebDriverFactory : RemoteWebDriverFactory() {

    override fun createDriver(): WebDriver {

        caps.browserName = "chrome"
        caps.version = "mobile-${getBrowserVersion()}"
        caps.setCapability("adbExecTimeout", 90000)

        val options = ChromeOptions()
        options.merge(caps)

        // options.setCapability("screenResolution", screenSize)

        //BUG in Android selenoid Image, 20.06.2019
        options.setCapability("enableVNC", false)

        options.setCapability("sessionTimeout", "15m")

        webDriver = RemoteWebDriver(URI.create("${getRemoteTestingServer()}/wd/hub").toURL(), options)
        webDriver.manage().window().maximize()
        return webDriver
    }

}
