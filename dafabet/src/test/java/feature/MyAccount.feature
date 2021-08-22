Feature: Dafabet MyAccount

  Scenario: Access MyAccount - Player Profile
    Given the player is at Dafabet - Entry site in EN language
    And the player clicks the Registration button
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
    And a new account is successfully created
    And the player clicks the MyAccount button
    Then the desktop MyAccount page is loaded successfully
    And the correct info are displayed
      | Username      |
      | First Name    |
      | Last Name     |
      | Date of Birth |
      | Currency      |
      | Country       |
      | Address       |
      | City          |
      | Postal Code   |

    # Update MyAccount Form
    When the player enters valid values in MyAccount Form
      | Address     | Test Address |
      | Town/City   | Test City    |
      | Postal Code | 1111         |
    And the player saves the MyAccount Form
    Then the player updates the MyAccount Form successfully
    And the correct info are displayed
      | Address       |
      | City          |
      | Postal Code   |

  Scenario: Access MyAccount - Change Password
    Given the player is at Dafabet - Entry site in EN language
    And the player clicks the Registration button
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
    And a new account is successfully created
    And the player clicks the Change Password button
    Then the desktop Change Password page is loaded successfully

    # Change Password
    When the player enters valid values in Change Password Form
      | New Password | qa456123 |
    And the player saves the Change Password Form
    Then the player changes the password successfully

    When the player logs out in Dafabet page
    And the player logs in using new - RMB desktop credentials
    Then the player is logged in successfully

  Scenario: Cant Login - Forgot Username
    Given the player is at Dafabet - Virtual-Sports site in EN language
    And the player clicks the Registration button
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
    And a new account is successfully created
    And the player logs out in Dafabet page
    When the player clicks the Cant Login button
    Then the desktop Forgot Password page is loaded successfully

    # Open Forgot Username tab
    When the player clicks the Forgot Username button
    Then the desktop Forgot Username page is loaded successfully

    # Submit Forgot Username
    When the player submits valid values in Forgot Username Form
    Then the Forgot Username Form is submitted successfully

    # Validate Email
    When the player closes the Cant Login window
    And the player opens Yopmail site
    And the player search for the email address
    Then the correct username is displayed in email

  Scenario: Cant Login - Forgot Password
    Given the player is at Dafabet - Virtual-Sports site in EN language
    And the player clicks the Registration button
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
    And a new account is successfully created
    And the player logs out in Dafabet page
    When the player clicks the Cant Login button
    Then the desktop Forgot Password page is loaded successfully

    # Submit Forgot Password
    When the player submits valid values in Forgot Password Form
    Then the Forgot Password Form is submitted successfully

    # Validate Email Receieved
    When the player closes the Cant Login window
    And the player opens Yopmail site
    And the player search for the email address
    Then the player received the Password Reset email

    # Reset Password
    When the player opens Reset Password link
    And the player submits valid values in Reset Password Form
    Then the Reset Password Form is submitted successfully

    # Login using NEW Credentials
    When the player is at Dafabet - Virtual-Sports site in EN language
    And the player logs in using new - RMB desktop credentials
    Then the player is logged in successfully