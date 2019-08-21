package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI

class RemoteAndroidWebDriverFactory : RemoteWebDriverFactory() {
    override fun createDriver(): WebDriver {

        caps.browserName = "android"
        caps.version = "8.1"
        caps.setCapability("enableVNC", true)
        caps.setCapability("enableVideo", false)
        //caps.setCapability("screenResolution", screenSize)
        caps.setCapability("sessionTimeout", "15m")

        webDriver = RemoteWebDriver(URI.create("${getRemoteTestingServer()}/wd/hub").toURL(), caps)
        return webDriver
    }

}