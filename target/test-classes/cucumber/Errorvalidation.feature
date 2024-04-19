
@tag
Feature: Error validation
  I want to use this template for my feature file

  @errorvalidation
  Scenario Outline: Title of your scenario outline
    Given I landed on ecommernce page
    When logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name             | password     | 
      | dirash@gmail.com | Dash@1234  | 
