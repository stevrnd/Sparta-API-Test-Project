@Get
Feature: As a user, I need to get all Spartans

  Scenario: Successfully retrieving a list of all Spartans
    Given the request is authorised
    When get all spartans request is made
    Then a 200 status code is given
    And the response body should be a JSON array containing Spartan objects

  Scenario: Failed to retrieve Spartans due to unauthorized access
    Given the request is unauthorised
    When get all spartans request is made
    Then a 401 status code is given