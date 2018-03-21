@Test11
Feature: Do XPath work
  #Sample Test Scenario Description

  @Test11
  Scenario Outline: Amazon App Search Test
    Given I clean and launch Android App by ID "com.amazon.mShop.android"
    And I wait "30" seconds for "skip.btn" to appear
    And I click "skip.btn" if present
    Then I click on "search.keywords"
    Then I press mobile "ENTER" key
    Then I enter "<searchWord>" to "search.keywords"
    Then I press mobile "ENTER" key
    And I wait for "5" seconds
    #And I click on "shopping.item1"
    #And I wait for "5" seconds
    #And I wait "30" seconds for "waitImage" to appear
    #Then I scroll down
    #Then I scroll down
    #Then I scroll down
    #Then I scroll down
    #Then I scroll to "add-cart"
    #And I wait for "5" seconds
    #Then I click on "add-cart"
    #Then I click on "action-bar-cart"
    #And I wait for "5" seconds
    #Then I click on "action-bar-cart"
    #Then I click on "delete.item"
    Then I close application by id "com.amazon.mShop.android"
    And I close application by id "com.android.chrome"

    Examples: {'datafile' : 'src/main/resources/data/AmazonTestData.csv'}









