# tests created by: Petyq Yaneva-Tsvetkova

@EntriesEndpoint
Feature: Test API Entries endpoint

  Background:
    * set endpoint for entries: entries

  @EntriesEndpointVerifyCount
  Scenario: Verification of all entries count
    Given We make a get request to the entries endpoint
    And Verify the Status code for entries endpoint
    When  Verify that count and count of all entries are the same
    Then Verify the entries count is equal to 1421

  @EntriesEndpointNotEmptyFields
  Scenario: Verify the value for the fields is not empty
    Given We make a get request to the entries endpoint
    When Verify the Status code for entries endpoint
    Then Verify that the values for the fields are not empty, except for Auth field for all of the entries


  @EntriesEndpointVerificationOfContent
  Scenario Outline: Verification of content of response with parameters
    Given We make a get request to the entries endpoint
    When Verify the Status code for entries endpoint
    Then Verify for all of the entries, that response contains <value> for param <parameter>
    Examples:
      | parameter   | value   |
      | title       | cat     |
      | description | Rwanda  |
      | auth        | apiKey  |
      | cors        | unknown |

   #   | category    | Art & Design | 400 Bad Request |


  @EntriesEndpointVerificationOfContentWithTwoParameters
  Scenario Outline: Get entry with given parameters and compare result
    Given We make a get request to the entries endpoint
    When Verify the Status code for entries endpoint
    Then Verify for all the entities, that the response contains <value1> for param <parameter1> and <value2> for param <parameter2>
    Examples:
      | parameter1 | value1 | parameter2  | value2 |
      | title      | cat    | description | Rwanda |

