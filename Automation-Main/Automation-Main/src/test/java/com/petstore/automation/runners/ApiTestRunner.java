package com.petstore.automation.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features/api",
glue = {"com.petstore.automation.stepDefinitions", "com.petstore.automation.hooks"},
tags = "@api",
plugin = {
"pretty",
"html:target/cucumber-reports/api-reports",
"json:target/cucumber-reports/api-cucumber.json",
"junit:target/cucumber-reports/api-cucumber.xml"
},
monochrome = true,
publish = false
)
public class ApiTestRunner {
}