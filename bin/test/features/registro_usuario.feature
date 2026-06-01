Feature: User registration in Parabank
  As a new customer of Parabank
  I want to create a bank account
  To access the online banking services and manage my finances

  Background:
    Given that the actor opens the Parabank home page

  Scenario: Successful user registration (Happy Path)
    When he registers a new user with valid information
    Then he should see the registration success message

  Scenario Outline: Try to register with invalid or incomplete data (Exceptional Cases)
    When he attempts to register with "<firstName>", "<lastName>", "<password>", and "<confirmPassword>"
    Then he should see the registration error in the "<field>" field as "<errorMessage>"

    Examples:
      | firstName | lastName | password | confirmPassword | field           | errorMessage                  |
      |           | Perez    | 12345678 | 12345678        | firstName       | First name is required.       |
      | Juan      |          | 12345678 | 12345678        | lastName        | Last name is required.        |
      | Juan      | Perez    | 12345678 | 87654321        | confirmPassword | Passwords did not match.      |