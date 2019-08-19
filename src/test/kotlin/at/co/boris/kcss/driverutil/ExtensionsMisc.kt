package at.co.boris.kcss.driverutil

import org.openqa.selenium.remote.RemoteWebDriver
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}


fun String.toBoolean(): Boolean {
    return when (toLowerCase()) {
        "true", "ja", "yes" -> true
        "false", "nein", "no" -> false
        else -> return false
    }
}

fun RemoteWebDriver.isMobile(): Boolean {

    val caps = this.capabilities

    if (caps.version.contains("mobile")) {
        return true
    }

    if (caps.browserName.contains("mobile")) {
        return true
    }

    if (caps.capabilityNames.contains("mobileEmulationEnabled")) {
        if (caps.getCapability("mobileEmulationEnabled") as Boolean) {
            return true
        }
    }

    if (System.getProperty("browser").contains("emulation")) {
        return true
    }

    return false
}