package com.petstore.automation.hooks;
import com.petstore.automation.config.TestContext;
import com.petstore.automation.ui.utils.WebDriverManager;
import com.petstore.automation.utils.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.List;
public class UiHooks {
@Before("@ui")
public void setUpUi(Scenario scenario) {
LoggerUtil.info("Setting up UI test environment for scenario: " + scenario.getName());
// Check if pet names are available from API tests
List<String> petNames = TestContext.get(TestContext.PET_NAMES);
if (petNames == null || petNames.isEmpty()) {
LoggerUtil.error("No pet names available from API tests. Skipping UI tests.");
throw new RuntimeException("UI tests cannot run without pet names from API tests");
}
WebDriverManager.initializeDriver();
LoggerUtil.info("WebDriver initialized successfully");
}
@After("@ui")
public void tearDownUi(Scenario scenario) {
LoggerUtil.info("Cleaning up UI test environment for scenario: " + scenario.getName());
if (scenario.isFailed()) {
LoggerUtil.error("Scenario failed: " + scenario.getName());
}
WebDriverManager.quitDriver();
LoggerUtil.info("WebDriver closed successfully");
}
}