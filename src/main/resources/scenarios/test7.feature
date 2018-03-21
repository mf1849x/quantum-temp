@Test7
Feature: Do XPath work
  #Sample Test Scenario Description

  @Test7
  Scenario:
    Given I open browser to webpage "http://nxc.co.il/demoaut/seats/seats.htm"
    When "seat-available" must exist
    Then I click on "seat-available"
    And "seat-reserved" must exist




