Feature: Get customer profile request

  Background:

  Scenario Outline: a user send a request to get all customers profile
  GET ../customerprofiles
    Given customer <customer-profile> with id <id> already exists in the system
    When when user send a get request to get customer's profile with id <id>
    Then a http status code <status> should be returned
    And response should contain : <customer-profile>

    Examples:
      | status | customer-profile                                                 | id |
      | 200    | {"firstName": "CustomerOneFName","lastName": "CustomerOneLName"} | 1  |
