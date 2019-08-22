package at.co.boris.kcss.driverutil

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI
import java.time.LocalDateTime

class RemoteChromeMobileEmulationWebDriverFactory : RemoteWebDriverFactory() {

    override fun createDriver(): WebDriver {

        caps.browserName = "chrome"
        caps.version = getBrowserVersion()

        val options = ChromeOptions()
        options.merge(caps)

        val mobileEmulation = HashMap<String, String>()
        mobileEmulation["deviceName"] = System.getProperty("emulated.device", emulatedDevices.Pixel_2.phoneName)
        options.setExperimentalOption("mobileEmulation", mobileEmulation)

        webDriver = RemoteWebDriver(URI.create("${getRemoteTestingServer()}/wd/hub").toURL(), options)
        return webDriver
    }

}
