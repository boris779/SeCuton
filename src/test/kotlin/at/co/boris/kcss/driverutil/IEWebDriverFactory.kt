package at.co.boris.kcss.driverutil

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

class IEWebDriverFactory : WebDriverFactory(){
    override fun createDriver(): WebDriver {


        val screenResolution = ScreenResolutions.valueOf(System.getProperty("viewport_resolution", "desktop_1440"))

        WebDriverManager.iedriver().version("3.9.0").setup()
        val options = InternetExplorerOptions()

        webDriver = InternetExplorerDriver(options)
        webDriver.manage().window().size = Dimension(screenResolution.width, screenResolution.height)

        return webDriver
    }

}
