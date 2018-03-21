@Test9
Feature: Do XPath work
  #Sample Test Scenario Description

  @Test9
  Scenario: Amazon App Search Test
    Given I clean and launch Android App by ID "com.amazon.mShop.android"
    When I switch to native context
    And I click on "skip.btn"
    Then I click on "search.keywords"
    Then I press mobile "ENTER" key
    Then I enter "skateboards" to "search.keywords"
    Then I press mobile "ENTER" key
    And I wait for "5" seconds
    And I click on "shopping.item"
    And I wait for "5" seconds
    Then I scroll to "//*[@resource-id="add-to-cart-button"]"
    And I wait for "5" seconds
    Then I click on "add-cart"
    Then I click on "action-bar-cart"
    And I wait for "5" seconds
    Then I switch to native context
    Then I click on "delete.item"
    Then I close application by id "com.amazon.mShop.android"
    And I close application by id "com.android.chrome"


    #And "validate.item" must exist
    #Then I click on "delete.item"







