package at.co.boris.kcss.driverutil

import logger
import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import java.awt.GraphicsDevice
import java.awt.GraphicsEnvironment
import java.util.concurrent.TimeUnit

object DriverFactory {

    private val log by logger()

    fun createWebDriver(scenarioId: String): WebDriver {

        val webDriver: WebDriver
        val browserName = System.getProperty("browser", DriverType.CHROME.toString()).toUpperCase()
        val driverType = DriverType.valueOf(browserName)

        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))
        val screenSize = screenResolution.resolution

        when (driverType) {
            DriverType.CHROME -> { //checked
                webDriver = ChromeWebDriverFactory().createDriver()
            }
            DriverType.FIREFOX -> { //checked
                webDriver = FirefoxWebDriverFactory().createDriver()
            }
            DriverType.EDGE -> { //checked
                webDriver = EdgeWebDriverFactory().createDriver()
            }
            DriverType.IE -> {
                webDriver = IEWebDriverFactory().createDriver()
            }
            DriverType.OPERA -> {
                webDriver = OperaWebDriverFactory().createDriver()
            }

            DriverType.CHROME_MOBILE_EMULATION -> { //checked
                webDriver = ChromeMobileEmulationWebDriverFactory().createDriver()
            }
            /* REMOTE Implementations */

            DriverType.REMOTE_CHROME_MOBILE_EMULATION -> { //checked
                webDriver = RemoteChromeMobileEmulationWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_OPERA -> { //checked
                webDriver = RemoteOperaWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_CHROME -> { //checked
                webDriver = RemoteChromeWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_FIREFOX -> { //checked
                webDriver = RemoteFirefoxWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_CHROME_MOBILE -> {
                webDriver = RemoteChromeMobileWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_ANDROID -> {
               webDriver = RemoteAndroidWebDriverFactory().createDriver()
            }
            DriverType.APPIUM_ANDROID_DEVICE -> {
                webDriver = AppiumAndroidWebDriverFactory().createDriver()
            }
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
            GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.forEach { log.debug(it.iDstring) }
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