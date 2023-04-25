@LeadCreation
Feature: Testing of Leads Creations



 Background:
    Given User login into D365 page
    When user enters valid username "userid"
    And Click on Next button
    When user enter valid password "pwd"
    And click on signin
    And Click on yes
    When user selects App "Sales Hub"


#@ValidateEntities
#Scenario: Validating entities in the customer sitemap
      #When user click on "Leads" under customer
      #Then user validates "Leads" under customer
      #Then user validates "My Open Leads" heading view
      
     #When user click on "Activities" under customer
      #Then user validates "Activities" under customer
      #Then user validates "My Activities" heading view
      #When user click on "Quotes" under customer
      #Then user validates "Quotes" under customer
      #Then user validates "My Quotes" heading view
      #When user click on "Opportunities" under customer
      #Then user validates "Opportunities" under customer
      #Then user validates "My Open Opportunities" heading view
      #When user click on "Orders" under customer
      #Then user validates "Orderss" under customer
      #Then user validates "My Orders" heading view
      #When user click on "Contacts" under customer
      #Then user validates "Leads" under customer
      #Then user validates "My Active Contacts" heading view
 @AddLead
  Scenario: Add new record under Leads and validate the Opportunity and Customer record
    When user click on "Leads" under customer
    Then user click on "New"
    And user fill "Topic" field as "topic29"
    Then user fill "Last Name" field As "l29"
    And user click Save
    When user click on "Leads" under customer
    Then user validates "l29" is showing in the "Leads" list
    And user click on "l29" lead
    Then user click "Qualify" bpf stage
    And user check the "Identify Decision Maker" field 
    Then user click Qualify
    When user click on "Opportunities" under customer
    Then user validates "topic29" is showing in the "Opportunity" list
    When user click "topic29" opportunity
    Then user click "Develop" bpf stage
    And user check the "Identify Stakeholders" field
    And user check the "Identify Competitors" field
    Then user click on Next Stage button under Develop BPF
    #Then user click on Propose bpf stage
    Then user click "Propose" bpf stage
    And user check the "Identify Sales Team" field
    And user check the "Develop Proposal" field
    And user check the "Complete Internal Review" field
    And user check the "Present Proposal" field
    Then user click on Next Stage under Propose BPF
    Then user click "Close" bpf stage
    And user check the "Complete Final Proposal" field
    And user check the "Present Final Proposal" field
    And user check the "Send Thank You" field
    And user check the "File De-brief" field
    Then user click on finish  
    
 
    #When user click on "Contacts" under customer
   #Then user validates "QA Leader 4" is showing in the "Contacts" list
  