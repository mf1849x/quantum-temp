@GSearch
Feature: Google Search

  @GSearch
  Scenario: Search on Google

    Given I close application by id "com.android.chrome"

    And I wait for "3" seconds
    Then I go to the device home screen
    And I try to close application by id "com.google.android.googlequicksearchbox"
    And I start application by id "com.google.android.googlequicksearchbox"
    Then I click "Say \"Ok Google\"" if present
    #And I wait for "10" seconds
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/say_ok_google"]"
    And I enter "Amazon" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds

    And I wait for "3" seconds
    Then Find me this string "books"
    And I wait for "3" seconds
    #Then I close application by id "com.android.chrome"
    #And I go to the device home screen

    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/clear_or_voice_button"]"
    And I enter "dogs" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds
    Then Find me this string "Dog"
    And I wait for "3" seconds

    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/clear_or_voice_button"]"
    And I enter "cats" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds
    Then Find me this string "Cat"
    And I wait for "3" seconds

    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/clear_or_voice_button"]"
    And I enter "bats" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds
    Then Find me this string "Bat"
    And I wait for "3" seconds

    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/clear_or_voice_button"]"
    And I enter "cars" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds
    Then Find me this string "Car"
    And I wait for "3" seconds

    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/clear_or_voice_button"]"
    And I enter "bitcoin" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds
    Then Find me this string "Bitcoin"
    And I wait for "3" seconds

    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I click on "//*[@resource-id="com.google.android.googlequicksearchbox:id/clear_or_voice_button"]"
    And I enter "footbikes" to "//*[@resource-id="com.google.android.googlequicksearchbox:id/search_box"]"
    Then I press Enter
    And I wait for "3" seconds
    Then Find me this string "footbikes"
    And I wait for "3" seconds