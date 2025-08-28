package com.petstore.automation.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features",
glue = {"com.petstore.automation.stepDefinitions", "com.petstore.automation.hooks"},
tags = "@api or @ui",
plugin = {
"pretty",
"html:target/cucumber-reports/all-reports",
"json:target/cucumber-reports/all-cucumber.json",
"junit:target/cucumber-reports/all-cucumber.xml"
},
monochrome = true,
publish = false
)
public class AllTestsRunner {
}
