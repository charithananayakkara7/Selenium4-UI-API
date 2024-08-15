package com.test.stepdef.api;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import serviceObjects.ChargePointService;

public class ChargePointTest{
	private Response response;
	public static String serial_id;
	private ChargePointService ChargePointService;

	@Given("I create a add serial number payload with {string}")
	public void addSerialNumPayload(String serialnumber) throws IOException {
		ChargePointService = new ChargePointService();
	   ChargePointService.addSerialNumPayload(serialnumber);
	}

	@When("I call the endpoint with the {string} method")
	public void makeRequest(String method) throws Exception {
	     response = ChargePointService.makeApiRequest(method);
			
	}

	@Then("I received a response with the status code {int}")
	public void reponseStatus(int expectedstatus) {
		assertEquals(response.getStatusCode(), expectedstatus);

	}

	@Then("I verify serial ID number is created sucessfully")
	public void verifySerialId() throws IOException {
		serial_id = ChargePointService.getJsonPath(response, "id");
		Assert.assertTrue(serial_id instanceof String, "The serial_id is not a string.");

	}

	@Then("{string} in response body is {string}")
	public void responseBodyVerify(String keyValue, String Expectedvalue) {
		assertEquals(ChargePointService.getJsonPath(response, keyValue), Expectedvalue);
	}

}
