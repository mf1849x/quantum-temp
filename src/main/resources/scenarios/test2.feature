@Test3
Feature: Search in Wikipedia for Apple

  #Scenario
  Scenario: Search Wiki

    Given I open browser to webpage "https://www.w3schools.com"
    Then I should see "w3schools.logo"
    Then I click on "hamburger.btn"
    And I click on "tutorials.lnk"
    Then I click on "learn.lnk"
    And I should see "xpathtutorial.show"