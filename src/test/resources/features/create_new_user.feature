Feature: Create a new user
  Scenario Outline: Verify creating user functionality
    Given The user sends "<name>" and "<job>" credantials
    When The user sends POST request to accessible create API endpoint
    Then The response status code should be 201
    And The user should see greater than zero id
    And The user should see created account in list of users
    Examples:
      | name  | job    |
      | Ozgur | tester |