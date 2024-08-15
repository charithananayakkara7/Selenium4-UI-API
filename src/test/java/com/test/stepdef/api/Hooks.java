package com.test.stepdef.api;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeleteSerialNum")
	public void beforeScenario() throws IOException, Exception
	{	
		
		ChargePointTest  ChargePointTest =new ChargePointTest();

			ChargePointTest.addSerialNumPayload("Test12345");
			ChargePointTest.makeRequest("POST");
			ChargePointTest.verifySerialId();
		


	}
}
