package at.co.boris.kcss.pageobjects

import at.co.boris.kcss.driverutil.WebDriverSession
import org.openqa.selenium.By

class TeamPage(session: WebDriverSession) : MainPage(session) {

    fun getTeamMembers(): MutableList<String> {

        val coreTeam = webDriver.findElement(By.xpath("//h2[@id='core-team'] /following-sibling::p"))

        return coreTeam.text.split("\n").toMutableList()
    }
}