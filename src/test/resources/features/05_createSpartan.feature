@Post
Feature: As a user, I need to post a spartan.

  Scenario: Successfully creating a new Spartan
    Given the request is authorised
    When post spartan request is sent using "validSpartanPostBody.json"
    Then a 200 status code is given

  Scenario: Failed to create a Spartan due to missing required fields
    Given the request is authorised
    When post spartan request is sent using "invalidSpartanPostBody.json"
    Then a 400 status code is given
    And the response body should contain an error message indicating the missing spartan field


  Scenario: Failed to create a Spartan due to an invalid course ID
    Given the request is authorised
    When post spartan request is sent using "invalidIdPostBody.json"
    Then a 400 status code is given
    And the response body should contain an error message indicating conversion from string to integer


  Scenario: Failed to create a Spartan due to unauthorized access
    Given the request is unauthorised
    When post spartan request is sent using "validSpartanPostBody.json"
    Then a 401 status code is given

