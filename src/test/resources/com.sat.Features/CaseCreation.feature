@CaseCreation
Feature: Creation of a case

  Background: 
    Given User login into D365 page
    When user enters valid username "userid"
    And Click on Next button
    When user enter valid password "pwd"
    And click on signin
    And Click on yes
    When user selects App "Customer Service Hub"
    When user click on "Cases" under Service

  @SingleCaseCreation
  Scenario: Create a single case
    And user clicked on "New Case"
    And user filled "Case" field as "Fund issue"
    And user fill all the details for "Customer" as "Adventure Works"
    And user click Save
    Then user validates case number is created

  @MultipleCasesCreation
  Scenario Outline: Creating multiple cases
    And user clicked on "New Case"
    And user filled "Case" field as "<Issuename>"
    And user fill all the details for "Customer" as "<Customername>"
    And user enters description
    And user click Save

    Examples: 
      | Issuename         | Customername    |
      | Defective product | Adventure Works |
      | Quality poor      | Adventure Works |
