package driverutil

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.MobilePlatform
import io.github.bonigarcia.wdm.WebDriverManager
import log
import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.*
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
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

    val browser: WebDriver
        get() {

            val webDriver: WebDriver
            val browserName = System.getProperty("browser", DriverType.CHROME.toString()).toUpperCase()
            val remoteTestingServer = System.getProperty("selenium.grid", "http://localhost:4444")
            val driverType = DriverType.valueOf(browserName)
            val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1920"))
            val videoRecording = System.getProperty("videoRecording", "no")
            val screenSize = screenResolution.resolution;

            val testId = "Test_" + LocalDateTime.now()


            when (driverType) {
                DriverType.CHROME -> {
                    WebDriverManager.chromedriver().setup()
                    val options = ChromeOptions()

                    webDriver = ChromeDriver(options)
                    webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
                }
                DriverType.FIREFOX -> {
                    WebDriverManager.firefoxdriver().setup()
                    webDriver = FirefoxDriver()
                    webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
                }
                DriverType.EDGE -> {
                    WebDriverManager.edgedriver().setup()
                    webDriver = EdgeDriver()
                    webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
                }
                DriverType.IE -> {
                    WebDriverManager.iedriver().setup()
                    webDriver = InternetExplorerDriver()
                    webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
                }
                DriverType.OPERA -> {
                    WebDriverManager.operadriver().setup()
                    webDriver = OperaDriver()
                    webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)
                }
                DriverType.REMOTE_CHROME -> {
                    val options = ChromeOptions()

                    if (videoRecording.toBoolean()) {
                        options.setCapability("enableVNC", true)
                        options.setCapability("enableVideo", true)
                        options.setCapability("videoName", testId + ".mp4")
                    }

                    options.setCapability("screenResolution", screenSize)
                    options.setCapability("sessionTimeout", "5m")

                    webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), options)
                    webDriver.manage().window().maximize()
                }
                DriverType.REMOTE_FIREFOX -> {
                    val capabilities = DesiredCapabilities()
                    capabilities.browserName = "firefox"
                    capabilities.setCapability("enableVNC", true)
                    capabilities.setCapability("enableVideo", true)
                    capabilities.setCapability("screenResolution", screenSize)
                    capabilities.setCapability("sessionTimeout", "5m")

                    webDriver = RemoteWebDriver(URI.create(remoteTestingServer + "/wd/hub").toURL(), capabilities)
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

            var topLeft: Point = Point(0, 0)

            try {
                if (!java.awt.GraphicsEnvironment.isHeadless()) {
                    val graphicsDevice = getGraphicsDevice()
                    topLeft = getTopLeftScreenPosition(graphicsDevice)
                }
            } catch (e: NoClassDefFoundError) {
                log().debug("Graphics settings not initialized!" + e)
            } catch (e: RuntimeException) {
                log().debug("Graphics settings not initialized!" + e)
            }

            webDriver.manage().window().position.moveBy(topLeft.x, topLeft.y)

            return webDriver
        }


    private fun getGraphicsDevice(): GraphicsDevice {
        val SCREEN_SYSTEM_PROPERTY = "screen"
        val prefscreen = System.getProperty(SCREEN_SYSTEM_PROPERTY)

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