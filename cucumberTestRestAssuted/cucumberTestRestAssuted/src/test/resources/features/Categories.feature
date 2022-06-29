# tests created by: Petyq Yaneva-Tsvetkova

@CategoriesEndpointUsingPOJO
Feature: Test API categories endpoint

  Background:
    * set endpoint for categories: categories

  @CategoriesEndpointCount
  Scenario: Verification of categories count
    Given We make a get request to the category endpoint
    When Verify Status code for categories endpoint is 200
    Then Verify that the CategoryPOJO's field for count and count of all categories list are the same
    And Verify the categories count is equal to 51

  @CategoriesEndpointContent
  Scenario Outline: Verifies that the content of the categories is correct
    Given We make a get request to the category endpoint
    When  Verify Status code for categories endpoint is 200
    Then Verify that the response contains <category>
    Examples:
      | category                       |
      | Animals                        |
      | Anime                          |
      | Anti-Malware                   |
      | Art & Design                   |
      | Authentication & Authorization |
      | Blockchain                     |
      | Books                          |
      | Business                       |
      | Calendar                       |
      | Cloud Storage & File Sharing   |
      | Continuous Integration         |
      | Cryptocurrency                 |
      | Currency Exchange              |
      | Data Validation                |
      | Development                    |
      | Dictionaries                   |
      | Documents & Productivity       |
      | Email                          |
      | Entertainment                  |
      | Environment                    |
      | Events                         |
      | Finance                        |
      | Food & Drink                   |
      | Games & Comics                 |
      | Geocoding                      |
      | Government                     |
      | Health                         |
      | Jobs                           |
      | Machine Learning               |
      | Music                          |
      | News                           |
      | Open Data                      |
      | Open Source Projects           |
      | Patent                         |
      | Personality                    |
      | Phone                          |
      | Photography                    |
      | Programming                    |
      | Science & Math                 |
      | Security                       |
      | Shopping                       |
      | Social                         |
      | Sports & Fitness               |
      | Test Data                      |
      | Text Analysis                  |
      | Tracking                       |
      | Transportation                 |
      | URL Shorteners                 |
      | Vehicle                        |
      | Video                          |
      | Weather                        |



