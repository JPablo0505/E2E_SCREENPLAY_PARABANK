Feature: Transfer funds between accounts in Parabank
  As a registered customer of Parabank
  I want to transfer funds between my accounts
  To manage my money effectively and securely

  Background:
    Given that the actor opens the Parabank home page
    And he registers a new user with valid information

  Scenario: Successful transfer of funds from checking to savings (Happy Path)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "SAVINGS" account using the initial checking as source
    And he transfers an amount of "50" from the first account to the new account
    Then he should see the transfer confirmation message
    And he should see the updated balance in the accounts overview

  Scenario: Try to transfer funds using invalid formats like letters (Exceptional Case)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "SAVINGS" account using the initial checking as source
    And he transfers an amount of "abc" from the first account to the new account
    Then he should see a validation error indicating only numeric values

  Scenario: Try to transfer a negative amount of money (Exceptional Case)
    And he verifies his initial checking account in the accounts overview
    When he opens a new "SAVINGS" account using the initial checking as source
    And he transfers an amount of "-50" from the first account to the new account
    Then the system should reject the transaction with an invalid amount error

  Scenario: Transfer funds to the same account (Edge Case - System Vulnerability)
    And he verifies his initial checking account in the accounts overview
    When he transfers an amount of "50" to the same account
    Then he should see the transfer confirmation message