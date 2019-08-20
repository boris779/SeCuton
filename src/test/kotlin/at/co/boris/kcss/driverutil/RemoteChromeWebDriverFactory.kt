package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI
import java.time.LocalDateTime

class RemoteChromeWebDriverFactory : WebDriverFactory() {

    override fun createDriver(): WebDriver {
        val caps = DesiredCapabilities()
        caps.version = getBrowserVersion()
        caps.browserName = "chrome"

        val options = ChromeOptions()
        options.merge(caps)

        val testId = "Test_" + LocalDateTime.now()

        val videoRecording = System.getProperty("videoRecording", "no")
        if (videoRecording.toBoolean()) {
            options.setCapability("enableVNC", true)
            options.setCapability("enableVideo", false)
            options.setCapability("videoName", "$testId.mp4")
        }

        // options.setCapability("screenResolution", screenSize)
        options.setCapability("sessionTimeout", "5m")

        val executionTag = System.getProperty("executionTag", "executionTag_not_set")
        options.setCapability("name", executionTag)

        val remoteTestingServer= System.getProperty("selenium.grid", "http://localhost:4444")

        webDriver = RemoteWebDriver(URI.create("$remoteTestingServer/wd/hub").toURL(), options)
        webDriver.manage().window().maximize()
        return webDriver
    }

}
