@Delete
Feature: Delete a Spartan by ID

  Scenario: Successfully deleting a Spartan by ID
    Given the request is authorised
    When a delete request is made for a Spartan with ID "1"
    Then a 204 status code is given

  Scenario: Failed to delete a Spartan due to an invalid ID
    Given the request is authorised
    When a delete request is made for a Spartan with ID "199"
    Then a 404 status code is given

  Scenario: Failed to delete a Spartan due to unauthorized access
    Given the request is unauthorised
    When a delete request is made for a Spartan with ID "2"
    Then a 401 status code is given