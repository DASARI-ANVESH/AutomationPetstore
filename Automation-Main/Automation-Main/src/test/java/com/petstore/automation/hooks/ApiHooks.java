package com.petstore.automation.hooks;
import com.petstore.automation.config.TestContext;
import com.petstore.automation.utils.LoggerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
public class ApiHooks {
@Before("@api")
public void setUpApi() {
LoggerUtil.info("Setting up API test environment");
RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
}
@After("@api")
public void tearDownApi() {
LoggerUtil.info("Cleaning up API test environment");
// Clean up any created test data if needed
TestContext.clear();
}
}