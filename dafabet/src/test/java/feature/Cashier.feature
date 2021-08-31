Feature: Dafabet Cashier

#  Scenario: Access Desktop Cashier
#    Given the player is at Dafabet - Entry site in EN language
#    And the player logs in using valid - RMB desktop credentials
#    And the player is logged in successfully
#    When the player clicks the Cashier button
#    Then the desktop Cashier page is loaded successfully

  Scenario: Mobile Cashier - Deposit / RMB
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

  Scenario: Mobile Cashier - Deposit / INR
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

  Scenario: Mobile Cashier - Deposit / THB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - THB mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    # Deposit - non-LBT Payment Method
    When the mobile player clicks the Deposit button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs deposit using GoCash88
      | Deposit To    | Casino         |
      | Bank          | Bangkok Bank   |
      | Amount        | 600            |
      | Transfer Type | Not Applicable |
      | Depositor     | Not Applicable |
      | Address       | Not Applicable |
    Then the mobile Deposit transaction is successful

  Scenario: Mobile Cashier - Fund Transfer / RMB
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

  Scenario: Mobile Cashier - Fund Transfer / INR
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

  Scenario: Mobile Cashier - Fund Transfer / THB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - THB mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

# Fund Transfer
    When the mobile player clicks the Fund Transfer button
    Then the mobile Fund Transfer page is loaded successfully

    When the mobile player performs fund transfer
      | Transfer From | OW Sports     |
      | Transfer To   | Common Wallet |
      | Amount        | 200.00        |
    Then the mobile Fund Transfer transaction is successful

    When the player goes back to the Mobile Cashier dashboard
    And the mobile player clicks the Fund Transfer button
    Then the mobile Fund Transfer page is loaded successfully

    When the mobile player performs fund transfer
      | Transfer From | Common Wallet |
      | Transfer To   | OW Sports     |
      | Amount        | 200.00        |
    Then the mobile Fund Transfer transaction is successful

  Scenario: Mobile Cashier - Withdraw - Bank Enrollment / RMB
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
      | Bank Account Number  | 111222333444        |
      | IFSC                 | Not Applicable      |
    Then the mobile Withdraw page is loaded successfully

  Scenario: Mobile Cashier - Withdraw - Bank Enrollment / INR
    Given the player is at MDafabet - Entry site in IN language
    And the mobile player clicks the Registration button
    And the desktop Registration page is loaded successfully
    When the player enters valid values with Username in mixed case
      | Password      | Qat456123     |
      | Email Domain  | @yopmail.com  |
      | Area Code     | India         |
      | Mobile Number | 1122334455    |
      | First Name    | Qatest        |
      | Last Name     | Test          |
      | Date of Birth | 01/01/1990    |
      | Currency      | INR           |
      | Country       | India         |
    Then a new account is successfully created

    When the player is at MDafabet - Entry site in IN language
    And the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player enrolls withdraw bank account
      | Bank Name            | Andhra Bank         |
      | Branch Name          | Branch Test Name    |
      | Branch Address       | Branch Test Address |
      | Bank Accoount Number | 111222333444        |
      | IFSC                 | TESTID12345         |
    Then the mobile Withdraw page is loaded successfully

  Scenario: Mobile Cashier - Withdraw - Bank Enrollment / THB
    Given the player is at MDafabet - Entry site in EN language
    And the mobile player clicks the Registration button
    And the desktop Registration page is loaded successfully
    When the player enters valid values with Username in uppercase
      | Password      | Qat456123     |
      | Email Domain  | @yopmail.com  |
      | Area Code     | Thailand      |
      | Mobile Number | 112233445     |
      | First Name    | Qatest        |
      | Last Name     | Test          |
      | Date of Birth | 01/01/1990    |
      | Currency      | THB           |
      | Country       | Thailand      |
    Then a new account is successfully created

    When the player is at MDafabet - Entry site in EN language
    And the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player enrolls withdraw bank account
      | Bank Name            | Bangkok Bank        |
      | Branch Name          | Branch Test Name    |
      | Branch Address       | Branch Test Address |
      | Bank Accoount Number | 111222333444        |
      | IFSC                 | TESTID12345         |
    Then the mobile Banking Details page is loaded successfully

  Scenario: Mobile Cashier - Successful Withdraw / RMB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - RMB mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the player checks the OW Sports balances
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs withdraw using Local Bank Transfer
      | Withdraw From   | OW Sports |
      | Withdraw Amount | 200       |
    Then the mobile Withdraw transaction is successful

  Scenario: Mobile Cashier - Successful Withdraw / INR
    Given the player is at MDafabet - Entry site in IN language
    And the player logs in using valid - INR mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the player checks the Casino balances
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs withdraw using Local Bank Transfer
      | Withdraw From   | Common Wallet |
      | Withdraw Amount | 500           |
    Then the mobile Withdraw transaction is successful

  Scenario: Mobile Cashier - Successful Withdraw / THB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - THB mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the player checks the OW Sports balances
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs withdraw using Local Bank Transfer
      | Withdraw From   | OW Sports |
      | Withdraw Amount | 500       |
    Then the mobile Withdraw transaction is successful

  Scenario: Mobile Cashier - Withdraw with Wagering / RMB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - RMB2 mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the player checks the Casino balances
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs withdraw using Local Bank Transfer
      | Withdraw From   | Casino |
      | Withdraw Amount | 200    |
    Then the mobile Withdraw transaction is not successful due to Wagering Requirement

  Scenario: Mobile Cashier - Withdraw with Wagering / INR
    Given the player is at MDafabet - Entry site in IN language
    And the player logs in using valid - INR2 mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the player checks the Casino balances
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs withdraw using Local Bank Transfer
      | Withdraw From   | Casino |
      | Withdraw Amount | 500    |
    Then the mobile Withdraw transaction is not successful due to Wagering Requirement

  Scenario: Mobile Cashier - Withdraw with Wagering / THB
    Given the player is at MDafabet - Entry site in EN language
    And the player logs in using valid - THB2 mobile credentials
    And the mobile player is logged in successfully
    When the mobile player clicks the Cashier button
    Then the mobile Cashier page is loaded successfully

    When the player checks the Casino balances
    And the mobile player clicks the Withdraw button
    Then the mobile Payment Options page is loaded successfully

    When the mobile player performs withdraw using Local Bank Transfer
      | Withdraw From   | Casino |
      | Withdraw Amount | 700    |
    Then the mobile Withdraw transaction is not successful due to Wagering Requirement