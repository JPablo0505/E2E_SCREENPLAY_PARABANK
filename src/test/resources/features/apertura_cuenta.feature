Feature: Open a new bank account in Parabank
  As a registered customer of Parabank
  I want to open a new savings account
  To manage my money and perform transactions

  Background:
    Given that the actor opens the Parabank home page
    And he registers a new user with valid information

  Scenario: Successful creation of a new savings account (Happy Path)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "SAVINGS" account using the initial checking as source
    Then he should see the successful account creation message

  Scenario: Successful creation of a new checking account (Happy Path)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "CHECKING" account using the initial checking as source
    Then he should see the successful account creation message

  Scenario: Verify the new savings account appears in the overview (Exceptional Case)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "SAVINGS" account using the initial checking as source
    Then he should see the successful account creation message
    And he should see the new account listed in the accounts overview

  Scenario: Verify the new checking account appears in the overview (Exceptional Case)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "CHECKING" account using the initial checking as source
    Then he should see the successful account creation message
    And he should see the new account listed in the accounts overview