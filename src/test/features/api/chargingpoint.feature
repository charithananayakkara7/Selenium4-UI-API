Feature:  Validating Charge Point Installation Form With API
@AddSerialNum
Scenario Outline:As a user, I should be able to add and delete serial number via an API call
	Given I create a add serial number payload with "<serial_number>"
    When I call the endpoint with the "POST" method
	And I received a response with the status code 201
	And I verify serial ID number is created sucessfully
	And  I call the endpoint with the "DELETE" method
	Then I received a response with the status code 204
	
	
	Examples:
    | serial_number |
    | Test123456    |
