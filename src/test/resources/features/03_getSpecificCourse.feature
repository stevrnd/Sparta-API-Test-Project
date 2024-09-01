@Get
Feature: As a user, I need to get a specific course by ID

  Scenario: Successfully retrieving a course by ID
    Given the request is authorised
    When the get course request is made to id "1"
    Then a 200 status code is given
    And a CourseDTO object is returned matching "course.json"

  Scenario: Failed to retrieve a course due to an invalid ID
    Given the request is authorised
    When the get course request is made to id "999"
    Then a 404 status code is given

  Scenario: Failed to retrieve a course due to unauthorized access
    Given the request is unauthorised
    When the get courses request is made
    Then a 401 status code is given