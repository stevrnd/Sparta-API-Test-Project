@Post
Feature: As a user, I need to log in.

  Scenario: Successful login with valid credentials
    Given the request contains valid username and password in the body
    When the request is sent
    Then a 200 status code is given
    And the response body should contain a valid authentication token

  Scenario: Failed login with invalid credentials
    Given the request contains an invalid username or password in the body
    When the request is sent
    Then a 401 status code is given