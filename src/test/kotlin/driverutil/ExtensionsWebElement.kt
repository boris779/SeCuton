package driverutil

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

fun WebElement.clearInput() {
    click()
    sendKeys(Keys.chord(Keys.CONTROL, "a"))
    sendKeys(Keys.BACK_SPACE)
}


