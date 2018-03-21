@WebMike
Feature: Search in Wikipedia for Apple

  #Scenario
  Scenario: Search Apple

    Given I open browser to webpage "www.wikipedia.com"
    Then I must see text "Wikipedia"
    Then I enter "Apple" to "xpath=//*[@id="searchInput"]"
    And I click on "xpath=//*[@class="pure-button pure-button-primary-progressive"]"
    Then I must see text "Apple tree"