Feature: Bank account management and operations in Parabank
  As a new customer of Parabank
  I want to register and manage my accounts
  To have full control over my financial transactions

  Background:
    Given that the actor opens the Parabank home page

  Scenario: Successful registration and account operations (Happy Path)
    When he registers a new user with valid information
    And he opens a new savings account
    And he transfers money into the new savings account
    Then he should see the updated balance in the accounts overview screen

Scenario Outline: Try to register with missing mandatory fields (Exceptional Case)
    When he attempts to register with "<firstName>", "<lastName>", "<password>", and "<confirmPassword>"
    Then he should see the registration error in the " <field>" field as "<errorMessage>"

    Examples:
      | firstName  | lastName | password  | confirmPassword | field     | errorMessage             |
      |            | Valencia | Udea2026* | Udea2026* | firstName | First name is required.  |
      | Juan Pablo |          | Udea2026* | Udea2026* | lastName  | Last name is required.   |
      | Juan Pablo | Valencia | Udea2026* | wrongPass       | password  | Passwords did not match. |