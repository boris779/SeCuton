package at.co.boris.kcss.driverutil

import assertk.assertThat
import assertk.assertions.contains
import io.github.bonigarcia.wdm.WebDriverManager
import logger
import org.openqa.selenium.Dimension
import org.openqa.selenium.Platform
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeDriverService
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File

class EdgeWebDriverFactory : WebDriverFactory(){
    override fun createDriver(): WebDriver {

        WebDriverManager.edgedriver().version(getDriverVersion()).setup()
        checkEnvironment()

        webDriver = EdgeDriver()
        webDriver.manage().window().size = screenDimension.dimension

        return webDriver
    }

    private fun checkEnvironment() {

        if (!System.getProperty("os.name").contains("Windows")) {
            log.warn("Your OS seems not to be Windows")
        }

        if (!System.getenv("Path").toLowerCase().contains("edge")) {
            log.warn("Your SystemPath seems not to contain edge binary")
        }

    }

}
