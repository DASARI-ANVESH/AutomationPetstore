@api
Feature: PetStore API Testing
As a test automation engineer
I want to test the PetStore API endpoints
So that I can ensure the API functionality works correctly
Background:
Given the PetStore API is available
@api @smoke
Scenario: Fetch top 5 unique pet names with available status
When I fetch pets with status "available"
Then I should get a valid JSON response with expected fields
When I retrieve the top 5 unique pet names
Then I should have 5 or fewer unique pet names
@api @crud
Scenario: Create pet, update status, and validate persistence
Given I create a pet with name "TestAutomationPet" and status "available"
When I update the pet status to "sold"
Then the pet status should be updated to "sold"
@api @negative
Scenario: Retrieve non-existent pet
When I retrieve a pet with non-existent ID "99999999"
Then the response code should be 404
And the response message should be "Pet not found"
@api @validation
Scenario: List pets by status and validate
When I fetch pets with status "available"
Then the response code should be 200
And at least one pet should have status "available"