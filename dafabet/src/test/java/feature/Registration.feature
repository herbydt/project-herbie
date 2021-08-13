Feature: Dafabet Registration

  Scenario: Registration in Dafabet Entry Page
    Given the player is at Dafabet - Entry site in EN language
    And the player clicks the Registration button
    And the Registration page is loaded successfully
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

  Scenario: Registration in Dafabet Casino Page
    Given the player is at Dafabet - Casino site in SC language
    And the player clicks the Registration button
    And the Registration page is loaded successfully
    When the player enters valid values with Username in uppercase
      | Password      | Qat456123     |
      | Email Domain  | @yopmail.com  |
      | Area Code     | 中国          |
      | Mobile Number | 11223344556   |
      | First Name    | Qatest        |
      | Last Name     | Test          |
      | Date of Birth | 1990/01/01    |
      | Currency      | 人民币        |
      | Country       | 中国          |
    Then a new account is successfully created

  Scenario: Registration in Dafabet Sportsbook Page
    Given the player is at Dafabet - Sports site in IN language
    And the player clicks the Registration button
    And the Registration page is loaded successfully
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

  Scenario: Registration in Dafabet Live Dealer Page
    Given the player is at Dafabet - Live-Dealer site in EN language
    And the player clicks the Registration button
    And the Registration page is loaded successfully
    When the player enters valid values with Username in lowercase
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