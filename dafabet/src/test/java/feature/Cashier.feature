Feature: Dafabet Cashier

  Scenario: Access Cashier
    Given the player is at Dafabet - Entry site in EN language
    And the player logs in using valid credentials
    And the player is logged in successfully
    When the player clicks the Cashier button
    Then the desktop Cashier page is loaded successfully

  Scenario: Access Mobile Cashier
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    # Deposit
    When the mobile player clicks the Deposit button
    Then the mobile Payment Options page is loaded successfully

    # Withdraw
    When the mobile player goes back to Mobile Cashier Dashboard
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully