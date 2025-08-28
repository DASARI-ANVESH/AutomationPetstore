package com.petstore.automation.stepDefinitions;
import com.petstore.automation.api.PetStoreApiClient;
import com.petstore.automation.api.models.Pet;
import com.petstore.automation.config.ConfigManager;
import com.petstore.automation.config.TestContext;
import com.petstore.automation.utils.LoggerUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
public class ApiStepDefinitions {
private PetStoreApiClient apiClient;
private Response response;
public ApiStepDefinitions() {
this.apiClient = new PetStoreApiClient();
}
@Given("the PetStore API is available")
public void thePetStoreAPIIsAvailable() {
LoggerUtil.info("Verifying PetStore API availability");
response = apiClient.getPetsByStatus("available");
Assert.assertEquals("API should be available", 200, response.getStatusCode());
LoggerUtil.info("PetStore API is available and responding");
}
@When("I fetch pets with status {{string}")
public void iFetchPetsWithStatus(String status) {
LoggerUtil.info("Fetching pets with status: " + status);
response = apiClient.getPetsByStatus(status);
TestContext.set(TestContext.API_RESPONSE, response);
LoggerUtil.info("API response received with status code: " + response.getStatusCode());
}
@Then("I should get a valid JSON response with expected fields")
public void iShouldGetAValidJSONResponseWithExpectedFields() {
Assert.assertEquals("Response should be successful", 200, response.getStatusCode());
Assert.assertTrue("Response should be JSON",
response.getContentType().contains("application/json"));
response.then()
.assertThat()
.body("[0].id", org.hamcrest.Matchers.notNullValue())
.body("[0].name", org.hamcrest.Matchers.notNullValue())
.body("[0].status", org.hamcrest.Matchers.notNullValue());
LoggerUtil.info("JSON response validation successful");
}
@When("I retrieve the top {int} unique pet names")
public void iRetrieveTheTopUniqueePetNames(int count) {
String status = ConfigManager.getProperty("pet.status.available");
LoggerUtil.info("Retrieving top " + count + " unique pet names with status: " + status);
List<String> petNames = apiClient.getTop5UniquePetNames(status);
TestContext.set(TestContext.PET_NAMES, petNames);
LoggerUtil.info("Retrieved " + petNames.size() + " unique pet names: " + petNames);
}
@Then("I should have {int} or fewer unique pet names")
public void iShouldHaveOrFewerUniquePetNames(int maxCount) {
List<String> petNames = TestContext.get(TestContext.PET_NAMES);
Assert.assertNotNull("Pet names should not be null", petNames);
Assert.assertTrue("Should have " + maxCount + " or fewer pet names",
petNames.size() <= maxCount);
Assert.assertFalse("Pet names list should not be empty", petNames.isEmpty());
LoggerUtil.info("Validation successful: " + petNames.size() + " unique pet names retrieved");
}
@Given("I create a pet with name {string} and status {string}")
public void iCreateAPetWithNameAndStatus(String name, String status) {
LoggerUtil.info("Creating pet with name: " + name + " and status: " + status);
Pet createdPet = apiClient.createTestPet(name, status);
TestContext.set(TestContext.CREATED_PET_ID, createdPet.getId());
LoggerUtil.info("Pet created successfully with ID: " + createdPet.getId());
}
@When("I update the pet status to {string}")
public void iUpdateThePetStatusTo(String newStatus) {
Long petId = TestContext.get(TestContext.CREATED_PET_ID);
LoggerUtil.info("Updating pet with ID: " + petId + " to status: " + newStatus);
Response getResponse = apiClient.getPetById(petId);
Pet pet = getResponse.as(Pet.class);
// Update status
pet.setStatus(newStatus);
response = apiClient.updatePet(pet);
LoggerUtil.info("Pet status update response code: " + response.getStatusCode());
}
@Then("the pet status should be updated to {string}")
public void thePetStatusShouldBeUpdatedTo(String expectedStatus) {
Long petId = TestContext.get(TestContext.CREATED_PET_ID);
LoggerUtil.info("Verifying pet status update for ID: " + petId);
Response getResponse = apiClient.getPetById(petId);
Assert.assertEquals("Pet should be retrieved successfully", 200, getResponse.getStatusCode());
Pet retrievedPet = getResponse.as(Pet.class);
Assert.assertEquals("Pet status should be updated", expectedStatus, retrievedPet.getStatus());
LoggerUtil.info("Pet status successfully updated to: " + expectedStatus);
}
@When("I retrieve a pet with non-existent ID {string}")
public void iRetrieveAPetWithNonExistentID(String petId) {
LoggerUtil.info("Attempting to retrieve pet with non-existent ID: " + petId);
response = apiClient.getPetById(Long.parseLong(petId));
LoggerUtil.info("Response received with status code: " + response.getStatusCode());
}
@Then("the response code should be {int}")
public void theResponseCodeShouldBe(int expectedStatusCode) {
Assert.assertEquals("Response code should match expected",
expectedStatusCode, response.getStatusCode());
LoggerUtil.info("Response code validation successful: " + expectedStatusCode);
}
@Then("the response message should be {string}")
public void theResponseMessageShouldBe(String expectedMessage) {
String actualMessage = response.jsonPath().getString("message");
Assert.assertEquals("Response message should match", expectedMessage, actualMessage);
LoggerUtil.info("Response message validation successful: " + expectedMessage);
}
@Then("at least one pet should have status {string}")
public void atLeastOnePetShouldHaveStatus(String expectedStatus) {Pet[] pets = response.as(Pet[].class);
Assert.assertTrue("At least one pet should have status " + expectedStatus,
java.util.Arrays.stream(pets)
.anyMatch(pet -> expectedStatus.equals(pet.getStatus())));
LoggerUtil.info("Validation successful: Found pets with status " + expectedStatus);
}
}
