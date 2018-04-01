package ryanair;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources",
        tags = {"@booking, @payment, @decline"},
        glue = {"ryanair.steps"},
        plugin = {"pretty", "html:target", "json:target/cucumber.json", "junit:target/cucumber.xml"})
public class TestRunner {
}