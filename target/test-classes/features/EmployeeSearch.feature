Feature: Employee search

  Background: these are common steps
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on employee list option

  @smoke @sprint2 @regression
  Scenario: Search employee by id
    #Given user is navigated to HRMS application
    When user enters valid employee id
    And user clicks on search button
    Then user is able to see the employee

  @regression @sprint30
  Scenario: Search employee by name
   # Given user is navigated to HRMS application
    When user enters name of the employee
    And user clicks on search button
    Then user is able to see the employee
