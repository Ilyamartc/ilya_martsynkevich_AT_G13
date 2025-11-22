Feature: Engineer Comparison
  As a test manager
  I want to compare different engineer types
  So that I can verify they have different characteristics

  Scenario: Compare automated and manual engineers
    Given I create an automated engineer with name "Auto" and experience 5
    And I create a manual engineer with name "Manual" and experience 5
    Then the automated engineer type should be "Automated"
    And the manual engineer type should be "Manual"
    And the engineer types should be different

  Scenario: Verify both engineer types can coexist
    Given I have the following engineers:
      | type      | name  | experience |
      | Automated | Eve   | 2          |
      | Manual    | Alice | 3          |
      | Automated | Dan   | 6          |
      | Manual    | Bob   | 5          |
    Then I should have 2 automated engineers
    And I should have 2 manual engineers
    And all engineers should have valid types
