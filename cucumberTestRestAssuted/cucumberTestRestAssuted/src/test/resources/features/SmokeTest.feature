# tests created by: Petyq Yaneva-Tsvetkova

@StatusCode
Feature:  verification of Status Code

  Background:
    Given get base URL: https://api.publicapis.org/

  @SmokeTest
  Scenario Outline: Verification of Status Code on different endpoint
    Given endpoint: <endpoint>
    When get Response Status
    Then Verify Status code is 200
    Examples:
      | endpoint   |
      | health     |
      | categories |
      | random     |
      | entries    |