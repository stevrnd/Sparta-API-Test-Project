@Put
Feature: As a user, I need to update a spartan by ID.

  Scenario: Successfully updating a Spartan by ID
    Given the request is authorised
    When a put request is made for "5" Spartan ID using: "updateSpartan.json"
    Then a 200 status code is given
    And the response body should match the updated Spartan object with the new data


  Scenario: Failed to update a Spartan due to an invalid ID
    Given the request is authorised
    When a put request is made for "999" Spartan ID using: "updateNonExistentIdSpartan.json"
    Then a 404 status code is given

  Scenario: Failed to update a Spartan due to validation errors
    Given the request is authorised
    When a put request is made for "2" Spartan ID using: "updateValidationError.json"
    Then a 400 status code is given
    And the response body should contain an error message indicating the validation errors

  Scenario: Failed to update a Spartan due to unauthorized access
    Given the request is unauthorised
    When a put request is made for "5" Spartan ID using: "updateSpartan.json"
    Then a 401 status code is given