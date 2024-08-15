package com.test.stepdef.ui;
import org.junit.Assert;

import BaseTestLayer.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ChargePointPage;

public class ChargePointTest extends BaseTest {
    private ChargePointPage ChargePoint;

    @After
    public void tearDown() throws Exception {
        browserClose();
    }
    @Given("I have accessed the charge point installation form")
    public void testSetup() throws Exception {
        initializeBrowser();
    }

    @When("I perform the add action on the serial number {string}")
    public void performActionOnSerialNumber(String serialnumber) throws Exception {
        ChargePoint = new ChargePointPage(driver, TIME_OUT);
        ChargePoint.addSerialNumber(serialnumber);
        ChargePoint.clickOnAddBtn();
    }

    @Then("I should be able to verify that a that a new serial number {string} has been added")
    public void VerifyNewData(String expectedSerialNumber) throws Exception {
        String actualSerialNumber = ChargePoint.verifyNewData(expectedSerialNumber);
        Assert.assertTrue("Charging Point Serial Number Mismatch!", actualSerialNumber.contains(expectedSerialNumber));
    }

    @When("I should be able to remove a serial number")
    public void RemoveData() throws Exception {
         ChargePoint.removeData();
    }
}
