Feature: Dafabet Login

  Scenario: Login to Dafabet Entry Page
    Given the player is at Dafabet site in EN language
    When the player logs in using valid credentials
    Then the player is logged in successfully