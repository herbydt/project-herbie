Feature: Access

  Scenario: Ghana - Verify display of Announcement Lightbox
  #Pre-Login
    Given the player is at Ghana desktop site in EN language
    When the player clicks Announcement icon
    Then the Announcement Lightbox is displayed