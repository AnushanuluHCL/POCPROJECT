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

  @CaseSearch
  Scenario: Search a creatyed case
    And user clicked on "New Case"
    And user filled "Case" field as "Fund issue"
    And user fill all the details for "Customer" as "Adventure Works"
    And user click Save
    Then user search for the case under "Cases" 

  