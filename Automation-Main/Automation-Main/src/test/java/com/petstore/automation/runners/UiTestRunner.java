package com.petstore.automation.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/resources/features/ui",
glue = {"com.petstore.automation.stepDefinitions", "com.petstore.automation.hooks"},
tags = "@ui",
plugin = {
"pretty",
"html:target/cucumber-reports/ui-reports",
"json:target/cucumber-reports/ui-cucumber.json",
"junit:target/cucumber-reports/ui-cucumber.xml"
},
monochrome = true,
publish = false
)
public class UiTestRunner {}