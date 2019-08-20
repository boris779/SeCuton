package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

abstract class DriverFactoryChrome : DriverFactory() {

    abstract var webDriver: WebDriver

    override fun getDriver(): WebDriver {

//        WebDriverManager.chromedriver().version(driverVersion).setup()
        val options = ChromeOptions()

        webDriver = ChromeDriver(options)
        //webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)


    }


}