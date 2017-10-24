Feature: Create customer profile request

  Background:

  Scenario Outline: a user post a request to create profile into the CRM system
  POST ../customerprofiles
    Given a user wants to create customer profile with <values>
    When when user post a request for create customer profile
    Then a http status code <status> should be returned

    Examples:
      | status | values                                            |
      | 201    | {"firstName": "FirstName","lastName": "LastName"} |
