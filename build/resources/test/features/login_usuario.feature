Feature: User login in Parabank
  As a registered customer of Parabank
  I want to log into the online banking platform
  To access my account summary and perform operations

  Background:
    Given that the actor opens the Parabank home page

  Scenario: Successful login with an existing user (Happy Path)
    When he logs in with username "john" and password "demo"
    Then he should see his accounts overview page

  Scenario Outline: Try to log in with invalid or incomplete credentials (Exceptional Cases)
    When he logs in with username "<username>" and password "<password>"
    Then he should see the login error message as "<errorMessage>"

    Examples:
      | username | password | errorMessage                          |
      |          | demo     | Please enter a username and password. |
      | john     |          | Please enter a username and password. |
      | errorUser| badPass  | The username and password could not be verified. |