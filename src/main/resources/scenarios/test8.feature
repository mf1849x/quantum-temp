@Test8
Feature: Do XPath work
  #Sample Test Scenario Description

  @Test8
  Scenario: Amazon Web Search Test
    Given I open browser to webpage "http://amazon.com"
    When I click on "search.keywords"
    And I enter "bike" to "search.keywords"
    Then I click on "search.btn"
    And I click on "search.results"
    Then I click on "cart"
    And "validate.item" must exist
    Then I click on "delete.item"






