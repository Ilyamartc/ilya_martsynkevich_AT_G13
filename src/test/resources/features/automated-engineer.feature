Feature: Automated Engineer Management
  As a test automation manager
  I want to manage automated engineers
  So that I can verify their properties and behavior

  Background:
    Given the engineer management system is ready

  Scenario: Create automated engineer with valid data
    Given I have an engineer name "Eve"
    And I have experience of 2 years
    When I create an automated engineer
    Then the engineer type should be "Automated"
    And the engineer name should be "Eve"
    And the engineer experience should be 2

  Scenario: Create multiple automated engineers
    Given I have an engineer name "Dan"
    And I have experience of 6 years
    When I create an automated engineer
    Then the engineer type should be "Automated"

  Scenario Outline: Verify automated engineer properties
    Given I have an engineer name "<name>"
    And I have experience of <experience> years
    When I create an automated engineer
    Then the engineer type should be "Automated"
    And the engineer name should be "<name>"
    And the engineer experience should be <experience>

    Examples:
      | name    | experience |
      | Eve     | 2          |
      | Dan     | 6          |
      | Charlie | 4          |
      | Alex    | 8          |

  Scenario: Automated engineer has correct type
    Given I create an automated engineer with name "TestAuto" and experience 5
    Then the engineer should not be null
    And the engineer type should be "Automated"
