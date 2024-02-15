Feature: User registration
  Scenario Outline: Successful registration
    Given The user has valid credantials as "<email>" and "<password>"
    When The user sends POST request to accessible register end point
    Then The response status code should be 200
    And The token shouldn't be null
    And The user should see greater than zero id
    Examples:
      | email              | password |
      | eve.holt@reqres.in | pistol   |
