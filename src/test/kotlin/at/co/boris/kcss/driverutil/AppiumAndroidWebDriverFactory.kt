package at.co.boris.kcss.driverutil

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.MobileCapabilityType
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import java.io.File
import java.net.URL

class AppiumAndroidWebDriverFactory : RemoteWebDriverFactory() {
    override fun createDriver(): WebDriver {
        WebDriverManager.chromedriver().version(getBrowserVersion()).setup()

        val path2chromeDriver = WebDriverManager.chromedriver().binaryPath.substringBeforeLast(File.separator)


        caps.setCapability("chromedriverExecutableDir", path2chromeDriver)
        caps.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.13")
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Appium_Android_Device")
        //caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1")
        caps.setCapability("browserName", "chrome")
        caps.setCapability(MobileCapabilityType.UDID, getMobileDeviceId())

        caps.setCapability("noReset", true)

        val appiumServer = URL("${getRemoteTestingServer()}/wd/hub")
        webDriver = AndroidDriver<AndroidElement>(appiumServer, caps)
        return webDriver
    }

    private fun getMobileDeviceId(): String {
        return System.getProperty("device.id", "emulator-5554")
    }

}