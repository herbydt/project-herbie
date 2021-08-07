Feature: Google Search

  Scenario: Google - Search for a keyword
    Given the player is at Google site in EN language
    When the player searches for DOG
    Then the search results are displayed