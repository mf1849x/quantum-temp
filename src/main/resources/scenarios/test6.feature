@Test6
Feature: Maps Example
  #Sample Test Scenario Description

  @Test6
  Scenario Outline:
    Given I restart Google Maps
    Then I click on "search_here.edit"
    And I want to search map on "<searchKey>"
    Then I should see text "<searchResult>"

    Examples:
      | recId | searchKey        | searchResult |
      | 1     | S       | Starbucks |
      | 2     | M       | McDonald's |