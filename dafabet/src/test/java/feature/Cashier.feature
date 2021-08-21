Feature: Dafabet Cashier

  Scenario: Access Cashier
    Given the player is at Dafabet - Entry site in EN language
    And the player logs in using valid - RMB credentials
    And the player is logged in successfully
    When the player clicks the Cashier button
    Then the desktop Cashier page is loaded successfully

  Scenario: Access Mobile Cashier - RMB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - RMB mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    # Deposit - non-LBT Payment Method
    When the mobile player clicks the Deposit button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs deposit using Easypay
      | Deposit To    | Common Wallet           |
      | Bank          | China Construction Bank |
      | Amount        | 1500.00                 |
      | Transfer Type | Internet Banking        |
      | Depositor     | Cashier Tester          |
      | Address       | Cashier Address         |
    Then the mobile Deposit transaction is successful

  Scenario: Access Mobile Cashier - INR
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - INR mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

  # Deposit - non-LBT Payment Method
    When the mobile player clicks the Deposit button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs deposit using AkontoPay
      | Deposit To    | Casino                |
      | Bank          | Central Bank of India |
      | Amount        | 600                   |
      | Transfer Type | Internet Banking      |
      | Depositor     | Cashier Tester        |
      | Address       | Cashier Address       |
    Then the mobile Deposit transaction is successful

#    When the mobile player performs deposit using Local Bank Transfer
#      | Deposit To    | Common Wallet     |
#      | Bank          | ICICI Bank        |
#      | Amount        | 1000.00           |
#      | Transfer Type | Internet Banking  |
#      | Depositor     | Cashier Tester    |
#      | Address       | Cashier Address   |
#    Then the mobile deposit request is successful
#
#    # Withdraw
#    When the mobile player goes back to Mobile Cashier Dashboard
#    And the mobile player clicks the Withdraw button
#    Then the mobile Payment Options page is loaded successfully