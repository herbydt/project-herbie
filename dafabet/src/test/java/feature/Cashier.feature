Feature: Dafabet Cashier

  Scenario: Access Cashier
    Given the player is at Dafabet site in EN language
    And the player logs in using valid credentials
    And the player is logged in successfully
    When the player clicks the Cashier button
    Then the Cashier page is loaded successfully