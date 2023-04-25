Feature: Testing Login Functionality

@loginL&G
  Scenario: Test CRM Login with valid credentials
    Given User login into L&G CRM page
    When user enters valid username "userid"
    And Click on Next button
    When user enter valid password "pwd"
    And click on signin
    And Click on yes
    When user selects App "Sales Hub"
    