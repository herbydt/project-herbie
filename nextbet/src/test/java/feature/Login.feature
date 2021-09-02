Feature: Nextbet Login

  Scenario: Login to Nextbet Entry Page
    Given the player is at Nextbet - Entry site in EN language
    When the player logs in using valid - RMB desktop credentials
    Then the player is logged in successfully