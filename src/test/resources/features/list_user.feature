Feature: List all users
  Scenario:Successfully List all users
    Given The API endpoint is available
    When The user send GET request to API endpoint
    Then The response status code should be 200
    And List of users is as in the following:
      | id | email                    | first_name | last_name |
      | 1  | george.bluth@reqres.in   | George     | Bluth     |
      | 2  | janet.weaver@reqres.in   | Janet      | Weaver    |
      | 3  | emma.wong@reqres.in      | Emma       | Wong      |
      | 4  | eve.holt@reqres.in       | Eve        | Holt      |
      | 5  | charles.morris@reqres.in | Charles    | Morris    |
      | 6  | tracey.ramos@reqres.in   | Tracey     | Ramos     |
