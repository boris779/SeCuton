import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/features"],
        strict = true,
        plugin = ["pretty", "html:target/cucumber/html-report", "json:target/cucumber/result.json", "pretty:target/cucuber/pretty.txt", "usage:target/cucumber/usage.json"]

        // glue = ["src/test/kotlin/cucumber/runtime/step_definitions"]
        // glue = ["src/test/resources/cucumber/steps"]
)

class RunCucumberTest {

}