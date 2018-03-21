@Test4
Feature: Calculator Example Feature
  #Sample Test Scenario Description

  @Test4
  Scenario: Calculator Addition Scenario
    Given I start application by name "com.sec.android.app.popupcalculator"
    And I am using an AppiumDriver
    When I click on "one.btn"
    And I click on "plus.btn"
    Then I click on "nine.btn"
    And I click on "equal.btn"
    Then I should see text "10"