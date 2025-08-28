package com.petstore.automation.stepDefinitions;
import com.petstore.automation.config.TestContext;
import com.petstore.automation.ui.pages.GoogleSearchPage;
import com.petstore.automation.ui.utils.WebDriverManager;
import com.petstore.automation.utils.LoggerUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;
public class UiStepDefinitions {
private GoogleSearchPage GoogleSearchPage;

private List<String> petNames;
public UiStepDefinitions() {
if (WebDriverManager.getDriver() != null) {
this.googleSearchPage = new GoogleSearchPage(WebDriverManager.getDriver());
}
}
@Given("I have pet names from the API test")
public void iHavePetNamesFromTheAPITest() {
petNames = TestContext.get(TestContext.PET_NAMES);
Assert.assertNotNull("Pet names should be available from API test", petNames);
Assert.assertFalse("Pet names list should not be empty", petNames.isEmpty());
LoggerUtil.info("Retrieved " + petNames.size() + " pet names from API test: " + petNames);
}
@Given("I am on Google search page")
public void iAmOnGoogleSearchPage() {
if (googleSearchPage == null) {
googleSearchPage = new GoogleSearchPage(WebDriverManager.getDriver());
}
googleSearchPage.navigateToGoogle();
LoggerUtil.info("Navigated to Google search page");
}
@When("I search for each pet name")
public void iSearchForEachPetName() {
Assert.assertNotNull("Pet names should be available", petNames);
for (String petName : petNames) {
Test Runners
LoggerUtil.info("Searching for pet name: " + petName);
googleSearchPage.searchFor(petName);
// Validate search results for current pet name
boolean resultsDisplayed = googleSearchPage.areSearchResultsDisplayed();
Assert.assertTrue("Search results should be displayed for: " + petName, resultsDisplayed);
int resultsCount = googleSearchPage.getSearchResultsCount();
LoggerUtil.info("Found " + resultsCount + " search results for: " + petName);
// Small delay between searches to avoid rate limiting
try {
Thread.sleep(2000);
} catch (InterruptedException e) {
Thread.currentThread().interrupt();
}
}
}
@Then("search results should be displayed for each pet name")
public void searchResultsShouldBeDisplayedForEachPetName() {
Assert.assertNotNull("Pet names should be available", petNames);
// This validation is already done in the previous step
// But we can add additional validations here if needed
LoggerUtil.info("Search results validation completed for all " + petNames.size() + " pet names");
}
@When("I search for pet name {string}")
public void iSearchForPetName(String petName) {
LoggerUtil.info("Searching for specific pet name: " + petName);
googleSearchPage.searchFor(petName);
}
@Then("search results should be displayed")
public void searchResultsShouldBeDisplayed() {
    boolean resultsDisplayed = googleSearchPage.areSearchResultsDisplayed();
Assert.assertTrue("Search results should be displayed", resultsDisplayed);
int resultsCount = googleSearchPage.getSearchResultsCount();
LoggerUtil.info("Search results displayed successfully. Count: " + resultsCount);
}
}
