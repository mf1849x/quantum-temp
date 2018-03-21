@Test12
Feature: Search in Wikipedia for Apple

  #Scenario
  Scenario: Search Apple

    Given I open browser to webpage "www.wikipedia.com"
    Then I enter "Cars" to "xpath=//*[@id="searchInput"]"
    Then I click "xpath=//*[@type="Submit"]" if present