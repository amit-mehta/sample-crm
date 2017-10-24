Feature: Get customer profile request

  Background:

  Scenario Outline: a user send a request to get all customers profile
  GET ../customerprofiles
    Given customers <existing-customer> are exists in the system
    When when user send a get request to get all customer's profile
    Then a http status code <status> should be returned
    And response should contain : <total> customers

    Examples:
      | status | existing-customer                                                                                                                   | total |
      | 200    | [{"id": "1", "firstName": "CustomerOneFName","lastName": "CustomerOneLName"},{"firstName": "CustomerTwoFName","lastName": "CustomerTwoLName"}] |  2    |
