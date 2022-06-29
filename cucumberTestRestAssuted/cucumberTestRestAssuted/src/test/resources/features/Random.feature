# tests created by: Petyq Yaneva-Tsvetkova

@RandomEndpointJsonPathApproach
Feature: Test API random endpoint

  Background:
    * set endpoint for random: random

  @RandomEndpointCount
  Scenario: Verification of random count
    Given We make a get request to the random endpoint
    And Verify the Status code for random endpoint
    When Get the count of all random in List and the count value from the response
    Then Verify that count from response and count of all random are the same
    And Verify the random entries count is equal to 1

  @RandomEndpointNotEmptyFields
  Scenario Outline: : Verify the value for the fields is not empty
    Given We make a get request to the random endpoint
    When Verify the Status code for random endpoint
    Then Verify that the values for the <fields> are not empty, except for Auth field
    Examples:
      | fields      |
      | API         |
      | Description |
      | HTTPS       |
      | Cors        |
      | Link        |
      | Category    |

  @RandomEndpointCountResponseWithParameters
  Scenario Outline: Verification of entries count when use response with parameters
    Given We make a get request to the random endpoint
    And Verify the Status code for random endpoint
    When Get random entry with the following parameters: <parameter> and <value>
    Then Verify that count and count of all random are the same <count>
    Examples:
      | parameter   | value        | count |
      | title       | cat          | 1     |
      | description | Rwanda       | 1     |
      | auth        | apiKey       | 1     |
      | https       | false        | 1     |
      | cors        | unknown      | 1     |
      | category    | Art & Design | 1     |

  @RandomEndpointVerificationOfContent
  Scenario Outline: Verification of content of response with parameters
    Given We make a get request to the random endpoint
    When Verify the Status code for random endpoint
    Then Verify that response contents <value> for param <parameter>
    Examples:
      | parameter   | value        |
      | title       | cat          |
      | description | Rwanda       |
      | auth        | apiKey       |
      | cors        | unknown      |
      | category    | Art & Design |

  @RandomEndpointVerificationOfContentWithTwoParameters
  Scenario Outline: Get entry with given parameters and compare result
    Given We make a get request to the random endpoint
    When Verify the Status code for random endpoint
    Then Verify for entry, that response contains <value1> for param <parameter1> and <value2> for param <parameter2>
    Examples:
      | parameter1 | value1 | parameter2  | value2 |
      | title      | cat    | description | Rwanda |


