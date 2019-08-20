package at.co.boris.kcss.driverutil

import org.openqa.selenium.remote.DesiredCapabilities
import java.time.LocalDateTime

abstract class RemoteWebDriverFactory: WebDriverFactory() {


    val caps = DesiredCapabilities()


    init {
        val videoRecording = System.getProperty("videoRecording", "no")
        val executionTag = System.getProperty("executionTag", "executionTag_not_set")

        caps.version = getBrowserVersion()

        caps.setCapability("sessionTimeout", "5m")
        caps.setCapability("enableVNC", true)
        caps.setCapability("name", executionTag)

        if (videoRecording.toBoolean()) {
            caps.setCapability("enableVideo", true)
            caps.setCapability("videoName", "Test_${LocalDateTime.now()}.mp4")
        }
    }



    fun getRemoteTestingServer(): String {
        return System.getProperty("selenium.grid", "http://localhost:4444")
    }

}

