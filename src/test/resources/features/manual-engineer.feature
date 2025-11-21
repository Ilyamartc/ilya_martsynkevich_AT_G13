Feature: Manual Engineer Management
  As a test manager
  I want to manage manual engineers
  So that I can verify their properties and behavior

  Background:
    Given the engineer management system is ready

  Scenario: Create manual engineer with valid data
    Given I have an engineer name "Alice"
    And I have experience of 3 years
    When I create a manual engineer
    Then the engineer type should be "Manual"
    And the engineer name should be "Alice"
    And the engineer experience should be 3

  Scenario: Create multiple manual engineers
    Given I have an engineer name "Bob"
    And I have experience of 5 years
    When I create a manual engineer
    Then the engineer type should be "Manual"

  Scenario Outline: Verify manual engineer properties
    Given I have an engineer name "<name>"
    And I have experience of <experience> years
    When I create a manual engineer
    Then the engineer type should be "Manual"
    And the engineer name should be "<name>"
    And the engineer experience should be <experience>

    Examples:
      | name  | experience |
      | Alice | 3          |
      | Bob   | 5          |
      | Sarah | 8          |
      | Mike  | 10         |

  Scenario: Manual engineer has correct type
    Given I create a manual engineer with name "TestManual" and experience 4
    Then the engineer should not be null
    And the engineer type should be "Manual"
