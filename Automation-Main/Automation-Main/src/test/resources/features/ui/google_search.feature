@ui
Feature: Google Search for Pet Names
As a test automation engineer
I want to search for pet names on Google
So that I can validate the search functionality works correctly
Background:
Given I have pet names from the API test
And I am on Google search page
@ui @integration
Scenario: Search for each pet name retrieved from API
When I search for each pet name
Then search results should be displayed for each pet name
@ui @individual
Scenario Outline: Search for individual pet names
When I search for pet name "<petName>"
Then search results should be displayed
Examples:
| petName |
| Fluffy |
| Max |
| Buddy |