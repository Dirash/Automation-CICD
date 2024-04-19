
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on ecommernce page

  @regression
  Scenario Outline: postive test of submitting the order 
    Given logged in with username <name> and password <password>
    When Add the product <productname> to cart
    And  Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message to be displayed in confirmationpage

    Examples: 
      | name             | password     | productname     |
      | dirash@gmail.com | Dirash@1234  | ADIDAS ORIGINAL |
                  
