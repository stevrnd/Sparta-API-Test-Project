@Get
Feature: As a user, I need to get a specific spartan by ID

  Scenario: Successfully retrieving a Spartan by ID
    Given the request is authorised
    When get specific spartan request is made for id "2"
    Then a 200 status code is given
    And the response body should be a JSON object: "existingId2.json"

  Scenario: Failed to retrieve a Spartan due to an invalid ID
    Given the request is authorised
    When get specific spartan request is made for id "999"
    Then a 404 status code is given

  Scenario: Failed to retrieve a Spartan due to unauthorized access
    Given the request is unauthorised
    When get specific spartan request is made for id "2"
    Then a 401 status code is given