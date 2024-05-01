@regression @smoke @smartbear
Feature: login functionality
@loginPositive
  Scenario: Validating login functionality with valid credentials
    Given user navigates to smartbear application
    When user logs in with username "Tester" and password "test"
    Then user is successfully logged in and the title is "Web Orders"

@loginNegative
  Scenario: Validating login functionality with invalid credentials
    Given user navigates to smartbear application
    When user logs in with username "Test" and password "Tester"
    Then user is not logged in and gets error message "Invalid Login or Password."