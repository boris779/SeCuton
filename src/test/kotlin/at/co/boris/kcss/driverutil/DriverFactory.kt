package at.co.boris.kcss.driverutil

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.remote.MobileCapabilityType
import io.github.bonigarcia.wdm.WebDriverManager
import logger
import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.opera.OperaDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.awt.GraphicsDevice
import java.awt.GraphicsEnvironment
import java.io.File
import java.net.URI
import java.net.URL
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

object DriverFactory {

    private val log by logger()

    fun createWebDriver(scenarioId: String): WebDriver {

        val webDriver: WebDriver
        val browserName = System.getProperty("browser", DriverType.CHROME.toString()).toUpperCase()
        val driverType = DriverType.valueOf(browserName)

        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))
        val screenSize = screenResolution.resolution

        // val browserVersion = System.getProperty("browser.version")
        // val remoteTestingServer = System.getProperty("selenium.grid", "http://localhost:4444")
        // val driverVersion = System.getProperty("driver.version")
        //val videoRecording = System.getProperty("videoRecording", "no")
        //val executionTag = System.getProperty("executionTag", "executionTag_not_set")

        when (driverType) {
            DriverType.CHROME -> {
                webDriver = ChromeWebDriverFactory().createDriver()
            }
            DriverType.FIREFOX -> {
                webDriver = FirefoxWebDriverFactory().createDriver()
            }
            DriverType.EDGE -> {
                webDriver = EdgeWebDriverFactory().createDriver()
            }
            DriverType.IE -> {
                webDriver = IEWebDriverFactory().createDriver()
            }
            DriverType.OPERA -> {
                webDriver = OperaWebDriverFactory().createDriver()
            }

            DriverType.CHROME_MOBILE_EMULATION -> {
                webDriver = ChromeMobileEmulationWebDriverFactory().createDriver()
            }
            /* REMOTE Implementations */
            DriverType.REMOTE_CHROME -> {
                webDriver = RemoteChromeWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_FIREFOX -> {
                webDriver = RemoteFirefoxWebDriverFactory().createDriver()
            }

   /*         DriverType.REMOTE_CHROME_MOBILE -> {
                val capabilities = DesiredCapabilities()
                capabilities.browserName = "chrome"
                capabilities.version = "mobile-$browserVersion"

                val chromeOptions = ChromeOptions()
                chromeOptions.merge(capabilities)
                //BUG in Android selenoid Image, 20.06.2019
                //chromeOptions.setCapability("enableVNC", true)
                chromeOptions.setCapability("sessionTimeout", "15m")

                webDriver = RemoteWebDriver(URI.create("$remoteTestingServer/wd/hub").toURL(), chromeOptions)


            } */



    /*        DriverType.REMOTE_CHROME_MOBILE_EMULATION -> {

                val capabilities = DesiredCapabilities()
                capabilities.browserName = "chrome"
                capabilities.version = browserVersion

                val chromeOptions = ChromeOptions()
                chromeOptions.merge(capabilities)
                chromeOptions.setCapability("enableVNC", true)
                chromeOptions.setCapability("name", executionTag)

                val mobileEmulation = HashMap<String, String>()
                mobileEmulation["deviceName"] = "noop"
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation)

                webDriver = RemoteWebDriver(URI.create("$remoteTestingServer/wd/hub").toURL(), chromeOptions)


            } */

    /*        DriverType.REMOTE_ANDROID -> {
                val capabilities = DesiredCapabilities()
                capabilities.browserName = "android"
                capabilities.version = "8.1"
                capabilities.setCapability("enableVNC", true)
                capabilities.setCapability("enableVideo", true)
                capabilities.setCapability("screenResolution", screenSize)
                capabilities.setCapability("sessionTimeout", "15m")

                webDriver = RemoteWebDriver(URI.create("$remoteTestingServer/wd/hub").toURL(), capabilities)
            }
            DriverType.ANDROID_DEVICE -> {

                WebDriverManager.chromedriver().version(driverVersion).setup()

                val path2chromeDriver = WebDriverManager.chromedriver().binaryPath.substringBeforeLast(File.separator)

                val capabilities = DesiredCapabilities()
                capabilities.setCapability("chromedriverExecutableDir", path2chromeDriver)
                capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.13")
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Appium_Android_Device")
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1")
                capabilities.setCapability("browserName", "chrome")
                capabilities.setCapability(MobileCapabilityType.UDID, System.getProperty("device.id", "emulator-5554"))

                capabilities.setCapability("noReset", true)

                val appiumServer = URL("$remoteTestingServer/wd/hub")
                webDriver = AndroidDriver<AndroidElement>(appiumServer, capabilities)
            } */
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)


     /*   if (driverType != DriverType.ANDROID_DEVICE) {

            var topLeft = Point(0, 0)

            try {
                if (!GraphicsEnvironment.isHeadless()) {
                    val graphicsDevice = getGraphicsDevice()
                    topLeft = getTopLeftScreenPosition(graphicsDevice)
                }
            } catch (e: NoClassDefFoundError) {
                log.debug("Graphics settings not initialized! $e")
            } catch (e: RuntimeException) {
                log.debug("Graphics settings not initialized! $e")
            }

            webDriver.manage().window().position.moveBy(topLeft.x, topLeft.y)

        } */

        return webDriver
    }


    private fun getGraphicsDevice(): GraphicsDevice {
        val screenSystemProperty = "screen"
        val prefscreen = System.getProperty(screenSystemProperty)

        if (System.getProperty("printScreens", "no") == "yes") {
            log.debug("#######################################")
            log.debug("Your screen id's: ")
            GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.forEach { log.debug(it.iDstring)}
            log.debug("#######################################")
        }


        if (StringUtils.isNoneBlank(prefscreen)) {
            val gdc = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices

            for (device in gdc) {
                if (prefscreen == device.iDstring) {
                    return device
                }
            }
        }
        return GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice
    }


    private fun getTopLeftScreenPosition(graphicsDevice: GraphicsDevice): Point {
        val topLeft = graphicsDevice.defaultConfiguration.bounds
        return Point(topLeft.x, topLeft.y)
    }
}