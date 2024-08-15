Feature: Validating Charge Point Installation Form With UI

  Background:
    Given I have accessed the charge point installation form

  Scenario Outline: As a user I should be able to add a serial number.
    When I perform the add action on the serial number "<serial_number>"
    Then I should be able to verify that a that a new serial number "<serial_number>" has been added

    Examples:
      | serial_number |
      | Test123456    |

  Scenario Outline: As a user I should be able to delete a serial number.
    When I perform the add action on the serial number "<serial_number>"
    And I should be able to verify that a that a new serial number "<serial_number>" has been added
    And I should be able to remove a serial number

    Examples:
      | serial_number |
      | Test13579    |
