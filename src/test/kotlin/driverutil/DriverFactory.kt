package driverutil

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.MobilePlatform
import io.github.bonigarcia.wdm.WebDriverManager
import logger
import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.*
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
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
        val browserVersion = System.getProperty("browser.version")
        val remoteTestingServer = System.getProperty("selenium.grid", "http://localhost:4444")
        val driverType = DriverType.valueOf(browserName)
        val driverVersion = System.getProperty("driver.version")
        val emulatedDevice = System.getProperty("emulated.device", "Pixel 2")
        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))
        val videoRecording = System.getProperty("videoRecording", "no")
        val screenSize = screenResolution.resolution;
        val executionTag = System.getProperty("executionTag", "executionTag_not_set")

        val testId = "Test_" + LocalDateTime.now()


        when (driverType) {
            DriverType.CHROME -> {
                WebDriverManager.chromedriver().version(driverVersion).setup()
                val options = ChromeOptions()

                webDriver = ChromeDriver(options)
                webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
            }
            DriverType.FIREFOX -> {
                WebDriverManager.firefoxdriver().version(driverVersion).setup()
                webDriver = FirefoxDriver()
                webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
            }
            DriverType.EDGE -> {
                WebDriverManager.edgedriver().version(driverVersion).setup()
                webDriver = EdgeDriver()
                webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
            }
            DriverType.IE -> {
                WebDriverManager.iedriver().setup()
                webDriver = InternetExplorerDriver()
                webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
            }
            DriverType.OPERA -> {
                WebDriverManager.operadriver().version(driverVersion).setup()
                webDriver = OperaDriver()
                webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
            }
            DriverType.REMOTE_CHROME -> {

                val caps = DesiredCapabilities()
                caps.version = browserVersion
                caps.browserName = "chrome"

                val options = ChromeOptions()
                options.merge(caps)


                if (videoRecording.toBoolean()) {
                    options.setCapability("enableVNC", true)
                    options.setCapability("enableVideo", false)
                    options.setCapability("videoName", testId + ".mp4")
                }

                options.setCapability("screenResolution", screenSize)
                options.setCapability("sessionTimeout", "5m")

                options.setCapability("name", executionTag)

                webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), options)
                webDriver.manage().window().maximize()
            }

            DriverType.REMOTE_CHROME_MOBILE -> {
                val capabilities = DesiredCapabilities()
                capabilities.browserName = "chrome"
                capabilities.version = "mobile-$browserVersion"

                val chromeOptions = ChromeOptions()
                chromeOptions.merge(capabilities)
                //BUG in Android selenoid Image, 20.06.2019
                //chromeOptions.setCapability("enableVNC", true)
                chromeOptions.setCapability("sessionTimeout", "15m")

                webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), chromeOptions)


            }

            DriverType.CHROME_MOBILE_EMULATION -> {

                WebDriverManager.chromedriver().version(driverVersion).setup()

                val capabilities = DesiredCapabilities()
                capabilities.browserName = "chrome"
                capabilities.version = browserVersion

                val chromeOptions = ChromeOptions();
                chromeOptions.merge(capabilities)

                val mobileEmulation = HashMap<String, String>();
                mobileEmulation["deviceName"] = emulatedDevice
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);


                webDriver = ChromeDriver(chromeOptions)
            }

            DriverType.REMOTE_CHROME_MOBILE_EMULATION -> {

                val capabilities = DesiredCapabilities()
                capabilities.browserName = "chrome"
                capabilities.version = browserVersion

                val chromeOptions = ChromeOptions();
                chromeOptions.merge(capabilities)
                chromeOptions.setCapability("enableVNC", true)
                chromeOptions.setCapability("name", executionTag)

                val mobileEmulation = HashMap<String, String>();
                mobileEmulation["deviceName"] = emulatedDevice
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

                webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), chromeOptions)


            }
            DriverType.REMOTE_FIREFOX -> {
                val capabilities = DesiredCapabilities()
                capabilities.browserName = "firefox"
                capabilities.setCapability("enableVNC", true)
                capabilities.setCapability("enableVideo", false)
                capabilities.setCapability("screenResolution", screenSize)
                capabilities.setCapability("sessionTimeout", "5m")
                capabilities.setCapability("name", executionTag)

                val options = FirefoxOptions()
                options.merge(capabilities)

                webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), options)
                webDriver.manage().window().maximize()
            }
            DriverType.REMOTE_ANDROID -> {
                val capabilities = DesiredCapabilities()
                capabilities.browserName = "android"
                capabilities.version = "8.1"
                capabilities.setCapability("enableVNC", true)
                capabilities.setCapability("enableVideo", true)
                capabilities.setCapability("screenResolution", screenSize)
                capabilities.setCapability("sessionTimeout", "15m")

                webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), capabilities)
            }
            DriverType.ANDROID_DEVICE -> {
                val capabilities = DesiredCapabilities()

                capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.1")
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Remote_Android_Device")

                capabilities.setCapability("browserName", "Chrome")
                capabilities.setCapability(MobileCapabilityType.UDID, System.getProperty("device.id", "e41f0310"))//"DEFAULT_ANDROID_DEVICE_ID"))

                capabilities.setCapability("noReset", "true")

                val appiumServer = URL(remoteTestingServer + "/wd/hub")
                webDriver = AndroidDriver<AndroidElement>(appiumServer, capabilities)
            }
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        var topLeft = Point(0, 0)

        try {
            if (!GraphicsEnvironment.isHeadless()) {
                val graphicsDevice = getGraphicsDevice()
                topLeft = getTopLeftScreenPosition(graphicsDevice)
            }
        } catch (e: NoClassDefFoundError) {
            log.debug("Graphics settings not initialized!" + e)
        } catch (e: RuntimeException) {
            log.debug("Graphics settings not initialized!" + e)
        }

        webDriver.manage().window().position.moveBy(topLeft.x, topLeft.y)

        return webDriver
    }


    private fun getGraphicsDevice(): GraphicsDevice {
        val SCREEN_SYSTEM_PROPERTY = "screen"
        val prefscreen = System.getProperty(SCREEN_SYSTEM_PROPERTY)

        if (System.getProperty("printScreens", "no").equals("yes")) {
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