package com.petstore.automation.api;
import com.petstore.automation.api.models.Category;
import com.petstore.automation.api.models.Pet;
import com.petstore.automation.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.*;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
public class PetStoreApiClient {
private final String baseUrl;
public PetStoreApiClient() {
this.baseUrl = ConfigManager.getProperty("api.base.url");
RestAssured.baseURI = baseUrl;
}
private RequestSpecification getRequestSpec() {
return given()
.contentType(ContentType.JSON)
.accept(ContentType.JSON);
}
public Response getPetsByStatus(String status) {
return getRequestSpec()
.queryParam("status", status)
.when()
.get("/pet/findByStatus");
}
public List<String> getTop5UniquePetNames(String status) {
Response response = getPetsByStatus(status);
if (response.getStatusCode() != 200) {
throw new RuntimeException("Failed to fetch pets with status: " + status);
}
List<Pet> pets = Arrays.asList(response.as(Pet[].class));
return pets.stream()
.map(Pet::getName)
.filter(Objects::nonNull)
.filter(name -> !name.trim().isEmpty())
.distinct()
.limit(5)
.collect(Collectors.toList());
}
public Response createPet(Pet pet) {
return getRequestSpec()
.body(pet)
.when()
.post("/pet");
}
public Pet createTestPet(String name, String status) {
Pet pet = new Pet();
pet.setId(System.currentTimeMillis()); // Use timestamp as ID
pet.setName(name);
pet.setStatus(status);
pet.setCategory(new Category(1L, "Test Category"));
pet.setPhotoUrls(Arrays.asList("http://example.com/photo1.jpg"));
Response response = createPet(pet);
if (response.getStatusCode() == 200) {
return response.as(Pet.class);
} else {
throw new RuntimeException("Failed to create pet: " + response.getBody().asString());
}
}
public Response updatePet(Pet pet) {
return getRequestSpec()
.body(pet)
.when()
.put("/pet");
}
public Response getPetById(Long petId) {
return getRequestSpec()
.when()
.get("/pet/" + petId);
}
public Response deletePet(Long petId) {
return getRequestSpec()
.when()
.delete("/pet/" + petId);
}
}
