@Get
Feature: As a user, I need to be able to get a list of all courses

  Scenario: Successfully retrieving a list of all courses
    Given the request is authorised
    When the get courses request is made
    Then a 200 status code is given
    And the response body should be a JSON array containing all courses

  Scenario: Failed to retrieve courses due to unauthorized access
    Given the request is unauthorised
    When the get courses request is made
    Then a 401 status code is given