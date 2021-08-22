Feature: Dafabet Cashier

  Scenario: Access Desktop Cashier
    Given the player is at Dafabet - Entry site in EN language
    And the player logs in using valid - RMB desktop credentials
    And the player is logged in successfully
    When the player clicks the Cashier button
    Then the desktop Cashier page is loaded successfully

  Scenario: Access Mobile Cashier - Deposit / RMB
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
      | Transfer Type | Not Applicable          |
      | Depositor     | Not Applicable          |
      | Address       | Not Applicable          |
    Then the mobile Deposit transaction is successful

  Scenario: Access Mobile Cashier - Fund Transfer / RMB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - RMB mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    # Fund Transfer
    When the mobile player clicks the Fund Transfer button
    Then the mobile Fund Transfer page is loaded successfully

    When the mobile player performs fund transfer
      | Transfer From | Dafa Sports   |
      | Transfer To   | Common Wallet |
      | Amount        | 1000.00       |
    Then the mobile Fund Transfer transaction is successful

    When the player goes back to the Mobile Cashier dashboard
    And the mobile player clicks the Fund Transfer button
    Then the mobile Fund Transfer page is loaded successfully

    When the mobile player performs fund transfer
      | Transfer From | Common Wallet |
      | Transfer To   | Dafa Sports   |
      | Amount        | 1000.00       |
    Then the mobile Fund Transfer transaction is successful

  Scenario: Access Mobile Cashier - Deposit / INR
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
      | Transfer Type | Not Applicable        |
      | Depositor     | Not Applicable        |
      | Address       | Not Applicable        |
    Then the mobile Deposit transaction is successful

  Scenario: Access Mobile Cashier - Fund Transfer / INR
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - INR mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    # Fund Transfer
    When the mobile player clicks the Fund Transfer button
    Then the mobile Fund Transfer page is loaded successfully

    When the mobile player performs fund transfer
      | Transfer From | OW Sports     |
      | Transfer To   | Common Wallet |
      | Amount        | 1500.00       |
    Then the mobile Fund Transfer transaction is successful

    When the player goes back to the Mobile Cashier dashboard
    And the mobile player clicks the Fund Transfer button
    Then the mobile Fund Transfer page is loaded successfully

    When the mobile player performs fund transfer
      | Transfer From | Common Wallet |
      | Transfer To   | OW Sports     |
      | Amount        | 1500.00       |
    Then the mobile Fund Transfer transaction is successful

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

  Scenario: Mobile Cashier - Withdraw - Bank Enrolment - RMB
    Given the player is at MDafabet - Entry site in EN language
    And the mobile player clicks the Registration button
    And the desktop Registration page is loaded successfully
    When the player enters valid values with Username in lowercase
      | Password      | Qat456123     |
      | Email Domain  | @yopmail.com  |
      | Area Code     | China         |
      | Mobile Number | 11223344556   |
      | First Name    | Qatest        |
      | Last Name     | Test          |
      | Date of Birth | 01/01/1990    |
      | Currency      | RMB/CNY       |
      | Country       | China         |
    Then a new account is successfully created

    When the player is at MDafabet - Entry site in EN language
    And the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player enrolls withdraw bank account
      | Bank Name            | Bank Of China       |
      | Branch Name          | Branch Test Name    |
      | Branch Address       | Branch Test Address |
      | Bank Accoount Number | 111222333444        |


#    When the mobile player performs withdraw using Local Bank Transfer
#      | Withdraw From   | Common Wallet |
#      | Withdraw Amount | 1000.00       |